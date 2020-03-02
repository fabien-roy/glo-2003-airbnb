# Flows

This document lists all flows for release 2.

Legend : 
- R1 : Release 1 (already done)
- \#x : Associated task number

`MapQuestClient` will be placed in `interfaces.infrastructure`. It must not know about beds.

## `POST /beds`
- `BedResource.add(...)` sends `BedRequest` to `BedService.add(...)` (R1)
  - `BedService.add(...)` maps valid bed using `BedMapper.fromRequest(...)` (R1)
    - **`BedMapper.fromRequest(...)` validates everything like in R1, but without zip code validation (#153)**
    - **`BedMapper.fromRequest(...)` validates lodgingMode if present (#154)**
    - **`BedMapper.fromRequest(...)` sets lodgingMode to "private" if not present (#154)**
  - **`BedService.add(...)` validates zip code using `MapQuestClient.validateZipCode(...)` (#155)**
    - **`MapQuestClient.validateZipCode(...)` validates zip code as string using external service (#156)**
  - `BedService.add(...)` has the same logic as in R1 after that (R1)
- `BedResource.add(...)` sets 201 as http status (R1)
- `BedResource.add(...)` sets new bed path as header location (R1)

## `GET /beds`
- `BedResource.getAll(...)` builds correct query map (R1)
  - **`BedResource.buildQueryMap(...)` must add `origin` if existing (#157)**
  - **`BedResource.buildQueryMap(...)` must add `maxDistance` if existing (#157)**
  - **`BedResource.buildQueryMap(...)` must add `arrivalDate` if existing (#157)**
  - **`BedResource.buildQueryMap(...)` must add `numberOfNights` if existing (#157)**
  - **`BedResource.buildQueryMap(...)` must add `lodgingMode` if existing (#157)**
- `BedResource.getAll(...)` sends query map to `BedService.getAll(...)` (R1)
  - `BedService.getAll(...)` builds `BedMatcher` using `BedMatcherMapper.fromRequestParams(...)` (R1)
    - `BedMatcherMapper.fromRequestParams(...)` has same logic as before (R1)
    - **`BedMatcherMapper.fromRequestParams(...)` validates `maxDistance` is more than 0 (#158)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `origin` is present if `maxDistance` is not null (#158)**
    - **`BedMatcherMapper.fromRequestParams(...)` sets `maxDistance` to 10 if `origin` is present and not `maxDistance` (#158)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `arrivalDate` has a valid format (#159)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `arrivalDate` is not in the past (#159)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `numberOfNights` is between 1 and 90 (#159)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `minCapacity` is present if `arrivalDate` is not null (#159)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `minCapacity` is present if `numberOfNights` is not null (#159)**
    - **`BedMatcherMapper.fromRequestParams(...)` sets `arrivalDate` to today if `minCapacity` is present and not `arrivalDate` (#159)**
    - **`BedMatcherMapper.fromRequestParams(...)` sets `numberOfNights` to 3 if `minCapacity` is present and not `numberOfNights` (#159)**
    - **`BedMatcherMapper.fromRequestParams(...)` validates `lodgingMode` is valid (#160)**
    - _NOTE : `origin` and `maxDistance` are attributes of `BedMatcher`, but aren't used in `BedMatcher.matches(...)`_
  - **`BedService.getAll(...)` validates zip code using `MapQuestClient.validateZipCode(...)` (#161)**
  - `BedService.getAll(...)` gets all beds using `BedRepository.getAll()` (R1)
  - **If origin is not null, use `MapQuestClient.isWithinRadius(String zipCode, String origin, double maxDistance)` on each bed to have a `List<Bed>` within radius. (#162)**
    - **`MapQuestClient.isWithinRadius(String zipCode, String origin, double maxDistance)` returns true or false using external service (#163)**
  - _NOTE : `BedMatcher` must be applied to list of beds after that, null origin or not._
  - `BedService.getAll(...)` uses `BedMatcher.matches(...)` to filter beds (R1)
    - **`BedMatcher.matches(...)` if `minCapacity` is set, use `Bed.isAvailable(...)` and work out some magic (#164)**
    - **`BedMatcher.matches(...)` if `lodgingMode` is set, checks if matches (#165)**
    - `BedMatcher.matches(...)` has the same logic as in R1 after that (R1)
  - `BedService.getAll(...)` uses `BedMapper.toResponse(...)` to map beds (R1)
- `BedResource.getAll(...)` sets 200 as http status (R1)
- `BedResource.getAll(...)` sets request body (R1)

## `GET /beds/:bedNumber`
- **Same as R1, except `BedMapper.toResponse(...)`, which now maps `logdingMode` (#166)**

## `POST /beds/:bedNumber/bookings`
- `BookingResource.add(...)` sends `BookingRequest` to `BookingService.add(...)` (R1)
  - _NOTE : What is not mentioned is the same as R1_
  - `BookingMapper.fromRequest` : 
    - **Validates `colonySize` (#167)**
    - **Maps `colonySize` (#167)**
  - **`BookingTotalCalculator.calculateTotal(...)` now calculates using colony size (#168)**
  - **`BookingFactory.create(...)` sets `status` to "booked" (#169)**
  - **`Bed.book(...)` now checks if cohabitation is possible if that is the lodging mode (#170)**
- `BookingResource.add(...)` sets 201 as http status (R1)
- `BookingResource.add(...)` sets new booking path as header location (R1)

## `GET /beds/:bedNumber/bookings/:bookingNumber`
- **Same as R1, except `BookingMapper.toResponse(...)`, which now maps `colonySize` (#171)**
- **Same as R1, except `BookingMapper.toResponse(...)`, which now maps `status` (#172)**

## `POST /beds/:bedNumber/bookings/:bookingNumber/cancel`
- **`BookingResource.cancel(...)` sends to `BookingService.cancel(String bedNumber, String bookingNumber)` (#173)**
  - **`BookingService.cancel(...)` gets booking in bed, if exists (#174)**
  - If not 24 hours prior to booking : 
    - **Throw and exception (#175)**
  - Else if not 7 days prior to booking : 
    - **Calculate half of price, making sure the greater half is for owner (#176)**
    - **`TransactionFactory.createStayCancelled(...)` for airbnb->tenant (#177)**
    - **`TransactionFactory.createStayCancelled(...)` for airbnb->owner (#177)**
    - **`TransactionFactory.createStayCancelled(...)` for owner->airbnb (#177)**
  - Else : 
    - **`TransactionFactory.createStayCancelled(...)` for airbnb->tenant (#178)**
    - **`TransactionFactory.createStayCancelled(...)` for owner->airbnb (#178)**
  - **`booking.addTransaction(...)` for newly created transactions (#179)**
  - **Set booking status to cancelled (#180)**
- **`BookingResource.cancel(...)` sets 200 as http status (#181)**

## `GET /admin/transactions`
- Same as R1
