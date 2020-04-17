# Flows

This document lists all flows for release 3. Issues concerning new features are linked one after the other, step by step.

## E1S6 / FFR

### `POST /beds/:bedNumber/bookings`
- `BookingResource.add(...)`
  - Maps `BookingRequest` with `BookingMapper`
  - Adds `BookingRequest` to bed with `BedService.add(...)`
    - ...
    - Adds stay booked transaction with `TransactionService.addStayBooked(...)`
      - Calculates total using service fee fro `ConfigurationService.get()`
      - If `Configuration.serviceFee` is null, returns same total ([#321](https://github.com/glo2003/glo2003-h2020-eq08/issues/321))
      - Else, returns total + total * serviceFee ([#321](https://github.com/glo2003/glo2003-h2020-eq08/issues/321))
    - ...
  - Sets OK as response status
  - Sets header location

### `POST /admin/transactions`
- `TransactionResource.configure(...)`
  - Maps `ConfigurationRequest` with `ConfigurationMapper` ([#322](https://github.com/glo2003/glo2003-h2020-eq08/issues/322))
    - Deserializes `ConfigurationRequest.serviceFee` using `ServiceFeeDeserializer` ([#323](https://github.com/glo2003/glo2003-h2020-eq08/issues/323))
      - Throws `InvalidServiceFeeException` if not positive integer ([#323](https://github.com/glo2003/glo2003-h2020-eq08/issues/323))
        - Responds associated `ErrorResponse` with `InvalidServiceFeeErrorFactory` ([#323](https://github.com/glo2003/glo2003-h2020-eq08/issues/323))
      - Throws `OutOfBoundsServiceFeeException` if not in [0, 15] ([#323](https://github.com/glo2003/glo2003-h2020-eq08/issues/323))
        - Responds associated `ErrorResponse` with `OutOfBoundsServiceFeeErrorFactory` ([#323](https://github.com/glo2003/glo2003-h2020-eq08/issues/323))
  - Sends `ConfigurationRequest` to `ConfigurationService.configure(...)` ([#322](https://github.com/glo2003/glo2003-h2020-eq08/issues/322))
      - Converts `ConfigurationRequest` to `Configuration` with `ConfigurationConverter.fromRequest(...)` ([#324](https://github.com/glo2003/glo2003-h2020-eq08/issues/324))
        - Sets `serviceFee` ([#325](https://github.com/glo2003/glo2003-h2020-eq08/issues/325))
      - Gets `Configuration` from `ConfigurationRepository.get()` ([#324](https://github.com/glo2003/glo2003-h2020-eq08/issues/324))
        - Gets `Configuration` ([#326](https://github.com/glo2003/glo2003-h2020-eq08/issues/326))
      - Updates `Configuration` with `Configuration.update(Configuration)` ([#324](https://github.com/glo2003/glo2003-h2020-eq08/issues/324))
        - Sets `serviceFee` to new value if the updated fee is not null ([#327](https://github.com/glo2003/glo2003-h2020-eq08/issues/327))
      - Sets `Configuration` with `ConfigurationRepository.set(Configuration)` ([#324](https://github.com/glo2003/glo2003-h2020-eq08/issues/324))
        - Replaces `Configuration` with new one ([#326](https://github.com/glo2003/glo2003-h2020-eq08/issues/326))
  - Sets OK as response status ([#322](https://github.com/glo2003/glo2003-h2020-eq08/issues/322))
  
## E4S1 / CRT

A few changes have to be made. `Transaction` will now have a `Bed` and `Booking`. It's absolutely not the best solution, but a lot shorter than setting up a whole reporting app. This is a big change to make before doing anything here. Some refactoring could also be done in packaging, like moving what concerns dates to interfaces. (#8)

Tip : look at `InMemoryBedQuery`, its filters and queries. It's pretty similar.

See the [reports package draft](reports.md) for information about new classes,

### `GET /admin/reports`
- `ReportResource.getAll(...)`
  - Gets news query param map with `QueryParamMapConverter.fromString(...)` ([#330](https://github.com/glo2003/glo2003-h2020-eq08/issues/330))
  - Gets all `ReportPeriodResponse` with `ReportService.getAll(...)` ([#330](https://github.com/glo2003/glo2003-h2020-eq08/issues/330))
    - Creates `ReportQuery` with `ReportQueryFactory.create(...)` ([#331](https://github.com/glo2003/glo2003-h2020-eq08/issues/331))
      - Gets new `ReportQueryBuilder` ([#335](https://github.com/glo2003/glo2003-h2020-eq08/issues/335))
      - For each `ReportQueryParamAssembler`, assemble (see [reports.md](reports.md)) ([#335](https://github.com/glo2003/glo2003-h2020-eq08/issues/335))
    - Gets report period with `Configuration.getReportPeriodForQuarters(scope)` ([#331](https://github.com/glo2003/glo2003-h2020-eq08/issues/331))
      - Default is 2020 ([#332](https://github.com/glo2003/glo2003-h2020-eq08/issues/332))
      - If scope is weekly, return periods in weeks ([#332](https://github.com/glo2003/glo2003-h2020-eq08/issues/332))
      - Else, return period ([#332](https://github.com/glo2003/glo2003-h2020-eq08/issues/332))
    - Gets `List<Transactions>` from `TransactionRepository.getAll(period)` ([#331](https://github.com/glo2003/glo2003-h2020-eq08/issues/331))
      - Return transactions with reservationTimestamp (or booking.arrivalDate?) within period ([#333](https://github.com/glo2003/glo2003-h2020-eq08/issues/333))
    - Sets transactions for query with `ReportQuery.setTransactions(transactions)` ([#331](https://github.com/glo2003/glo2003-h2020-eq08/issues/331))
    - Gets `List<ReportPeriod>` with `ReportQuery.execute()` ([#331](https://github.com/glo2003/glo2003-h2020-eq08/issues/331))
      - For each `ReportPeriod` in `periods`  ([#329](https://github.com/glo2003/glo2003-h2020-eq08/issues/329))
        - Get `List<Transaction>` from list, where reservationTimestamp is in `ReportPeriod`
        - Create `List<ReportPeriodData>` with a single item, containing all transactions for period
        - For each `ReportDimension` in `dimensions`
          - `List<ReportPeriodData>` equals `ReportDimension.split(List<ReportPeriodData>)`
        - For each `ReportPeriodData` in `reportPeriodData`
            - For each `ReportMetric` in `metrics`
              - `ReportMetric.calculate(ReportPeriodData)`
    - Converts all `ReportPeriod` to `ReportPeriodResponse` with `ReportConverter.toResponse(...)` ([#331](https://github.com/glo2003/glo2003-h2020-eq08/issues/331))
      - Converts all fields from domain to REST response ([#334](https://github.com/glo2003/glo2003-h2020-eq08/issues/334))
    - Returns all `ReportPeriodResponse` ([#331](https://github.com/glo2003/glo2003-h2020-eq08/issues/331))
  - Sets OK as response status ([#330](https://github.com/glo2003/glo2003-h2020-eq08/issues/330))
  - Returns all `ReportPeriodResponse` ([#330](https://github.com/glo2003/glo2003-h2020-eq08/issues/330))
  
### `DELETE /admin`
- `AdminResource.delete(...)`
  - Calls `AdminService.delete()` ([#336](https://github.com/glo2003/glo2003-h2020-eq08/issues/336))
      - Calls `BedService.delete()` ([#337](https://github.com/glo2003/glo2003-h2020-eq08/issues/337))
          - Calls `BedRepository.delete()` ([#337](https://github.com/glo2003/glo2003-h2020-eq08/issues/337))
              - Flushes list of beds ([#337](https://github.com/glo2003/glo2003-h2020-eq08/issues/337))
      - Calls `TransactionService.delete()` ([#337](https://github.com/glo2003/glo2003-h2020-eq08/issues/337))
          - Calls `TransactionRepository.delete()` ([#337](https://github.com/glo2003/glo2003-h2020-eq08/issues/337))
              - Flushes list of transactions ([#337](https://github.com/glo2003/glo2003-h2020-eq08/issues/337))
  - Sets OK as response status ([#336](https://github.com/glo2003/glo2003-h2020-eq08/issues/336))
