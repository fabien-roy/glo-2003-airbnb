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
      - If `Configuration.serviceFee` is null, returns same total (#1)
      - Else, returns total + total * serviceFee (#1)
    - ...
  - Sets OK as response status
  - Sets header location

### `POST /admin/transactions`
- `TransactionResource.configure(...)`
  - Maps `ConfigurationRequest` with `ConfigurationMapper` (#2)
    - Deserializes `ConfigurationRequest.serviceFee` using `ServiceFeeDeserializer` (#3)
      - Throws `InvalidServiceFeeException` if not positive integer (#3)
        - Responds associated `ErrorResponse` with `InvalidServiceFeeErrorFactory` (#3)
      - Throws `OutOfBoundsServiceFeeException` if not in [0, 15] (#3)
        - Responds associated `ErrorResponse` with `OutOfBoundsServiceFeeErrorFactory` (#3)
  - Sends `ConfigurationRequest` to `ConfigurationService.configure(...)` (#2)
      - Converts `ConfigurationRequest` to `Configuration` with `ConfigurationConverter.fromRequest(...)` (#4)
        - Sets `serviceFee` (#5)
      - Gets `Configuration` from `ConfigurationRepository.get()` (#4)
        - Gets `Configuration` (#6)
      - Updates `Configuration` with `Configuration.update(Configuration)` (#4)
        - Sets `serviceFee` to new value if the updated fee is not null (#7)
      - Sets `Configuration` with `ConfigurationRepository.set(Configuration)` (#4)
        - Replaces `Configuration` with new one (#6)
  - Sets OK as response status (#2)
  
## E4S1 / CRT

A few changes have to be made. `Transaction` will now have a `Bed` and `Booking`. It's absolutely not the best solution, but a lot shorter than setting up a whole reporting app. This is a big change to make before doing anything here. Some refactoring could also be done in packaging, like moving what concerns dates to interfaces. (#8)

Tip : look at `InMemoryBedQuery`, its filters and queries. It's pretty similar.

See the [reports package draft](reports.md) for information about new classes,

### `GET /admin/reports`
- `ReportResource.get(...)`
  - Gets news query param map with `QueryParamMapConverter.fromString(...)` (#10)
  - Gets all `ReportPeriodResponse` with `ReportService.getAll(...)` (#10)
    - Creates `ReportQuery` with `ReportQueryFactory.create(...)` (#11)
      - Gets new `ReportQueryBuilder` (#15)
      - For each `ReportQueryParamAssembler`, assemble (see [reports.md](reports.md)) (#15)
    - Gets report period with `Configuration.getReportPeriodForQuarters(scope)` (#11)
      - Default is 2020, in quarters (#12)
      - Default is 2020 (#12)
    - Gets `List<Transactions>` from `TransactionRepository.getAll(period)` (#11)
      - Return transactions with timestamp (or booking.arrivalDate?) within period (#13)
    - Sets transactions for query with `ReportQuery.setTransactions(transactions)` (#11)
    - Gets `List<ReportPeriod>` with `ReportQuery.execute()` (#11)
      - For each `ReportPeriod` in `periods`  (#9, all nested)
        - Get `List<Transaction>` from list, where timestamp is in `ReportPeriod`
        - Create `List<ReportPeriodData>` with a single item, containing all transactions for period
        - For each `ReportDimension` in `dimensions`
          - `List<ReportPeriodData>` equals `ReportDimension.split(List<ReportPeriodData>)`
        - For each `ReportPeriodData` in `reportPeriodData`
            - For each `ReportMetric` in `metrics`
              - `ReportMetric.calculate(ReportPeriodData)`
    - Converts all `ReportPeriod` to `ReportPeriodResponse` with `ReportConverter.toResponse(...)` (#11)
      - Converts all fields from domain to REST response (#14)
    - Returns all `ReportPeriodResponse` (#11)
  - Sets OK as response status (#10)
  - Returns all `ReportPeriodResponse` (#10)
  
### `DELETE /admin`
- `AdminResource.delete(...)`
  - Calls `AdminService.delete()` (#16)
      - Calls `BedService.delete()` (#17)
          - Calls `BedRepository.delete()` (#17)
              - Flushes list of beds (#17)
      - Calls `TransactionService.delete()` (#17)
          - Calls `TransactionRepository.delete()` (#17)
              - Flushes list of transactions (#17)
  - Sets OK as response status (#16)
