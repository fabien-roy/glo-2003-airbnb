# Issues

Temporary issues separation for release 3.

## E1S6 / FFR

- 1 : [E1S6/FFR] : Add booking fee in transaction service
  - [ ] If `Configuration.serviceFee` is null, returns same total
  - [ ] Else, returns total + total * serviceFee
  
- 2 : [E1S6/FFR] : Add path to configure transaction resource
  - [ ] Maps `ConfigurationRequest` with `ConfigurationMapper`
  - [ ] Sends `ConfigurationRequest` to `ConfigurationService.configure(...)`
  - [ ] Sets OK as response status
  
- 3 : [E1S6/FFR] : Deserialize configuration request
  - [ ] Deserializes `ConfigurationRequest.serviceFee` using `ServiceFeeDeserializer`
  - [ ] Throws `InvalidServiceFeeException` if not positive integer
    - [ ] Responds associated `ErrorResponse` with `InvalidServiceFeeErrorFactory`
  - [ ] Throws `OutOfBoundsServiceFeeException` if not in [0, 15]
    - [ ] Responds associated `ErrorResponse` with `OutOfBoundsServiceFeeErrorFactory`
    
- 4 : [E1S6/FFR] : Set up configuration service
  - [ ] Converts `ConfigurationRequest` to `Configuration` with `ConfigurationConverter.fromRequest(...)`
  - [ ] Gets `Configuration` from `ConfigurationRepository.get()`
  - [ ] Updates `Configuration` with `Configuration.update(Configuration)`
  - [ ] Sets `Configuration` with `ConfigurationRepository.set(Configuration)`
  
- 5 : [E1S6/FFR] : Convert configuration request
  - [ ] Sets `serviceFee`
  
- 6 : [E1S6/FFR] : Set up configuration in repository
  - [ ] `.get()`
  - [ ] `.update(...)` : Replaces `Configuration` with new one
  
- 7 : [E1S6/FFR] : Change service fee of configuration
  - [ ] Default value is null
  - [ ] Sets `serviceFee` to new value if the updated fee is not null
  
## E4S1 / CRT

- 8 : [E1S4/CRT] : Refactor transactions to hold associated bed and booking
  - A few changes have to be made. `Transaction` will now have a `Bed` and `Booking`. It's absolutely not the best solution, but a lot shorter than setting up a whole reporting app. This is a big change to make before doing anything here. Some refactoring could also be done in packaging, like moving what concerns dates to interfaces.

- 9 : [E1S4/CRT] : Set up report queries
  - For each `ReportPeriod` in `periods`
    - Get `List<Transaction>` from list, where timestamp is in `ReportPeriod`
    - Create `List<ReportPeriodData>` with a single item, containing all transactions for period
    - For each `ReportDimension` in `dimensions`
      - `List<ReportPeriodData>` equals `ReportDimension.split(List<ReportPeriodData>)`
    - For each `ReportPeriodData` in `reportPeriodData`
        - For each `ReportMetric` in `metrics`
          - `ReportMetric.calculate(ReportPeriodData)`
  
- 10 : [E1S6/FFR] : Add path to configure report resource
  - [ ] Gets news query param map with `QueryParamMapConverter.fromString(...)`
  - [ ] Gets all `ReportPeriodResponse` with `ReportService.getAll(...)`
  - [ ] Sets OK as response status
  - [ ] Returns all `ReportPeriodResponse`
  
- 11 : [E1S6/FFR] : Set up report service
  - [ ] Creates `ReportQuery` with `ReportQueryFactory.create(...)`
  - [ ] Gets report period with `Configuration.getReportPeriod()`
  - [ ] Gets `List<Transactions>` from `TransactionRepository.getAll(period)`
  - [ ] Sets transactions for query with `ReportQuery.setTransactions(transactions)`
  - [ ] Gets `List<ReportPeriod>` with `ReportQuery.execute()`
  - [ ] Converts all `ReportPeriod` to `ReportPeriodResponse` with `ReportConverter.toResponse(...)`
  - [ ] Returns all `ReportPeriodResponse`
  
- 12 : [E1S6/FFR] : Get report period with configuration
  - [ ] Default is 20
  
- 13 : [E1S6/FFR] : Get all transactions within period
  - [ ] Return transactions with timestamp (or booking.arrivalDate?) within period
  
- 14 : [E1S6/FFR] : Convert report periods to responses
  - [ ] Converts all fields from domain to REST response
  
- 15 : [E1S6/FFR] : Build report query using param assemblers
  - [ ] Gets new `ReportQueryBuilder`
  - [ ] For each `ReportQueryParamAssembler`, assemble (see [reports.md](reports.md))
  
- 16 : [E1S6/FFR] : Add path to delete everything
  - [ ] Calls `AdminService.delete()`
  - [ ] Sets OK as response status
  
- 16 : [E1S6/FFR] : Add service to delete content of repositories
  - [ ] Calls `BedService.delete()` 
    - [ ] Calls `BedRepository.delete()` 
      - [ ] Flushes list of beds 
  - [ ] Calls `TransactionService.delete()` 
    - [ ] Calls `TransactionRepository.delete()` 
      - [ ] Flushes list of transactions 
  - Calls `ConfigurationService.delete()` (unsure : [#320](https://github.com/glo2003/glo2003-h2020-eq08/issues/320))
    - Calls `ConfigurationRepository.delete()` (unsure)
      - Flushes configuration (unsure)
