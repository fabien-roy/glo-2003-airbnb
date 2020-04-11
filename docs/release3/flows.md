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
      - If `Configuration.serviceFee` is null, returns same total (#x)
      - Else, returns total + total * serviceFee (#x)
    - ...
  - Sets OK as response status
  - Sets header location

### `POST /admin/transactions`
- `TransactionResource.configure(...)`
  - Maps `ConfigurationRequest` with `ConfigurationMapper` (#x)
    - Deserializes `ConfigurationRequest.serviceFee` using `ServiceFeeDeserializer` (#x)
      - Throws `InvalidServiceFeeException` if not positive integer (#x)
        - Responds associated `ErrorResponse` with `InvalidServiceFeeErrorFactory` (#x)
      - Throws `OutOfBoundsServiceFeeException` if not in [0, 15] (#x)
        - Responds associated `ErrorResponse` with `OutOfBoundsServiceFeeErrorFactory` (#x)
  - Sends `ConfigurationRequest` to `ConfigurationService.configure(...)` (#x)
      - Converts `ConfigurationRequest` to `Configuration` with `ConfigurationConverter.fromRequest(...)` (#x)
        - Sets `serviceFee` (#x)
      - Gets `Configuration` from `ConfigurationRepository.get()` (#x)
        - Gets `Configuration` (#x)
      - Updates `Configuration` with `Configuration.update(Configuration)` (#x)
        - Sets `serviceFee` to new value if the updated fee is not null (#x)
      - Sets `Configuration` with `ConfigurationRepository.set(Configuration)` (#x)
        - Replaces `Configuration` with new one (#x)
  - Sets OK as response status (#xoft
  
## E4S1 / CRT

A few changes have to be made. `Transaction` will now have a `Bed` and `Booking`. It's absolutely not the best solution, but a lot shorter than setting up a whole reporting app. This is a big change to make before doing anything here. Some refactoring could also be done in packaging, like moving what concerns dates to interfaces. (#x)

Tip : look at `InMemoryBedQuery`, its filters and queries. It's pretty similar.

See the [reports package draft](reports.md) for information about new classes,

### `GET /admin/reports`
- `ReportResource.getAll(...)`
  - Gets news query param map with `QueryParamMapConverter.fromString(...)` (#x)
  - Gets all `ReportPeriodResponse` with `ReportService.getAll(...)` (#x)
    - Creates `ReportQuery` with `ReportQueryFactory.create(...)` (#x)
      - Gets new `ReportQueryBuilder` (#x)
      - For each `ReportQueryParamAssembler`, assemble (see [reports.md](reports.md)) (#x)
    - Gets `Map<BookingPeriod, List<Transactions>>` from `TransactionRepository.getAll(Configuration.getReport)`
    - Sets transactions for query with `ReportQuery.setTransactions(transactions)`
    - Gets `List<ReportPeriod>` with `ReportQuery.execute()` (#FABIEN)
      - For each `ReportPeriod` in `periods`
        - Get `List<Transaction>` from list, where timestamp is in `ReportPeriod`
        - Create `List<ReportPeriodData>` with a single item, containing all transactions for period
        - For each `ReportDimension` in `dimensions`
          - `List<ReportPeriodData>` equals `ReportDimension.split(List<ReportPeriodData>)`
        - For each `ReportPeriodData` in `reportPeriodData`
            - For each `ReportMetric` in `metrics`
              - `ReportMetric.calculate(ReportPeriodData)`
    - Converts all `ReportPeriod` to `ReportPeriodResponse` with `ReportConverter.toResponse(...)` (#x)
      - Converts all fields from domain to REST response (#x)
    - Returns all `ReportPeriodResponse` (#x)
  - Sets OK as response status (#x)
  - Returns all `ReportPeriodResponse` (#x)
  
### `DELETE /admin`
- `AdminResource.delete(...)`
  - Calls `AdminService.delete()` (#x)
      - Calls `BedService.delete()` (#x)
          - Calls `BedRepository.delete()` (#x)
              - Flushes list of beds (#x)
      - Calls `TransactionService.delete()` (#x)
              - Flushes list of transactions (#x)
      - Calls `ConfigurationService.delete()` (unsure : [#320](https://github.com/glo2003/glo2003-h2020-eq08/issues/320))
              - Flushes configuration (unsure)
  - Sets OK as response status (#x)
