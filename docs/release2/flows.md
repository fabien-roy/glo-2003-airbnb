# Flows

This document lists all flows for release 2.

Legend : 
- R1 : Release 1 (already done)
- \#? : Since this document is done before tasks, tasks to be made are referenced this way

`MapQuestClient` will be placed in `interfaces.clients`. It must not know about beds.

## `POST /beds`
- `BedResource.add(...)` sends `BedRequest` to `BedService.add(...)` (R1)
  - `BedService.add(...)` maps valid bed using `BedMapper.fromRequest(...)` (R1)
    - **`BedMapper.fromRequest(...)` validates everything like in R1, but removes zip code validation (#?)**
    - **`BedMapper.fromRequest(...)` validates lodgingMode if present (#?)**
    - **`BedMapper.fromRequest(...)` sets lodgingMode to "private" if not present (#?)**
  - **`BedService.add(...)` validates zip code using `MapQuestClient.validateZipCode(...)` (#?)**
    - **`MapQuestClient.validateZipCode(...)` validates zip code as string using external service (#?)**
  - `BedService.add(...)` has the same logic as in R1 after that (R1)
- `BedResource.add(...)` sets 201 as http status (R1)
- `BedResource.add(...)` sets new bed path as header location (R1)

## `GET /beds`
- `BedResource.getAll(...)` builds correct query map (R1)
  - **`BedResource.buildQueryMap(...)` must add `origin` if existing (#?)**
  - **`BedResource.buildQueryMap(...)` must add `maxDistance` if existing (#?)**
  - **`BedResource.buildQueryMap(...)` must add `arrivalDate` if existing (#?)**
  - **`BedResource.buildQueryMap(...)` must add `numberOfNights` if existing (#?)**
  - **`BedResource.buildQueryMap(...)` must add `lodgingMode` if existing (#?)**
- `BedResource.getAll(...)` sends query map to `BedService.getAll(...)` (R1)
  - `BedService.getAll(...)` builds `BedMatcher` using `BedMatcherMapper.fromRequestParams(...)` (R1)
    - `BedMatcherMapper.fromRequestParams(...)` has same logic as before (R1)
    - **`BedMatcherMapper.fromRequestParams(...)` validates `maxDistance` is more than 0 (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `origin` is present if `maxDistance` is not null (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` sets `maxDistance` to 10 if `origin` is present and not `maxDistance` (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `arrivalDate` has a valid format (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `arrivalDate` is not in the past (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `numberOfNights` is between 1 and 90 (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `minCapacity` is present if `arrivalDate` is not null (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `minCapacity` is present if `numberOfNights` is not null (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` sets `arrivalDate` to today if `minCapacity` is present and not `arrivalDate` (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` sets `numberOfNights` to 3 if `minCapacity` is present and not `numberOfNights` (#?)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `lodgingMode` is valid (#?)**
    - _NOTE : `origin` and `maxDistance` are attributes of `BedMatcher`, but aren't used in `BedMatcher.matches(...)`_
  - **`BedService.getAll(...)` validates zip code using `MapQuestClient.validateZipCode(...)` (#?)**
  - `BedService.getAll(...)` gets all beds using `BedRepository.getAll()` (R1)
  - **If origin is not null, use `MapQuestClient.isWithinRadius(String zipCode, String origin, double maxDistance)` on each bed to have a `List<Bed>` within radius. (#?)**
    - **`MapQuestClient.isWithinRadius(...)` returns true or false using external service (#?)**
  - _NOTE : `BedMatcher` must be applied to list of beds after that, null origin or not._
  - `BedService.getAll(...)` uses `BedMatcher.matches(...)` to filter beds (R1)
    - **`BedMatcher.matches(...)` if `minCapacity` is set, `Bed.isAvailable(...)` (#?) -> FABIEN : IMMA TAKE THAT**
    - **`BedMatcher.matches(...)` if `lodgingMode` is set, checks if matches (#?)**
    - `BedMatcher.matches(...)` has the same logic as in R1 after that (R1)
  - `BedService.getAll(...)` uses `BedMapper.toResponse(...)` to map beds (R1)
- `BedResource.getAll(...)` sets 200 as http status (R1)
- `BedResource.getAll(...)` sets request body (R1)

## `GET /beds/:bedNumber`
- **Same as R1, except `BedMapper.toResponse(...)`, which now maps `logdingMode` (#?)**

## `POST /beds/:bedNumber/bookings`
- `BookingResource.add(...)` sends `BookingRequest` to `BookingService.add(...)` (R1)
  - _NOTE : What is not mentioned is the same as R1_
  - `BookingMapper.fromRequest` : 
    - **Validates `colonySize` (#?)**
    - **Maps `colonySize` (#?)**
  - **`BookingTotalCalculator.calculateTotal(...)`, now calculates using colony size (#?)**
  - **`BookingFactory.create(...)`, sets `status` to "booked" (#?)**
  - **`Bed.book(...)`, now checks if cohabitation is possible if that is the lodging mode (#?) -> FABIEN : IMMA TAKE THAT**
- `BookingResource.add(...)` sets 201 as http status (R1)
- `BookingResource.add(...)` sets new booking path as header location (R1)

## `GET /beds/:bedNumber/bookings/:bookingNumber`
- **Same as R1, except `BookingMapper.toResponse(...)`, which now maps `colonySize` (#?)**
- **Same as R1, except `BookingMapper.toResponse(...)`, which now maps `status` (#?)**

## `POST /beds/:bedNumber/bookings/:bookingNumber/cancel`
- **`BookingResource.cancel(...)` sends to `BookingService.cancel(String bedNumber, String bookingNumber)` (#?)**
  - **`BookingService.cancel(...)` gets booking in bed, if exists (#?)**
  - If not 24 prior to booking : 
    - **Throw and exception (#?)**
  - Else if not 7 days prior to booking : 
    - **Calculate half of price, making sur the greater half is for owner (#?)**
    - **`TransactionFactory.createStayCancelled(...)` for airbnb->tenant (#?)**
    - **`TransactionFactory.createStayCancelled(...)` for airbnb->owner (#?)**
    - **`TransactionFactory.createStayCancelled(...)` for owner->airbnb (#?)**
  - Else : 
    - **`TransactionFactory.createStayCancelled(...)` for airbnb->tenant (#?)**
    - **`TransactionFactory.createStayCancelled(...)` for owner->airbnb (#?)**
  - **`booking.addTransaction(...)` for newly created transactions (#?)**
- **`BedResource.getAll(...)` sets 200 as http status (#?)**

## `GET /admin/transactions`
- Same as R1
