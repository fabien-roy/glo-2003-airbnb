# Technical Requirements

This document lists all technical requirements for release 2.

## E1S4 (FLG)
- When adding a bed (`POST /beds`)
  - Zip code must be an existing US postal code
    - _This replaces our validation in `BedMapper`. Instead of checking if zipCode is 5 digits in the REST layer, we will ask `ZippopotamusClient.validateZipCode(String)` during `BedService.add(...)`._
- When getting all beds (`GET /beds`)
  - Beds can be filtered by origin
    - If max distance if not specified, default is 10km
    - Origin must be an existing US postal code
    - _We will use `DistanceCalculator.isWithinRadius(String, String, double)` to filter beds by location. Maybe a method passing all current beds and returning the ones with a close enough zip code_
  - Beds can be filtered by maximum distance
    - Maximum distance must be more than 0
    - Origin must be specified

## E1S5 (FLC)
- When getting all beds (`GET /beds`)
  - Beds can be filtered by minimal capacity
    - _This changes from the previous behaviour. The bed has to have a capacity big enough for its capacity minus bookings colony sizes for each day of stay. Some beds are private, some are in cohabitation. Check epic 3._
    - Minimal capacity must be more than 0
    - If arrival date is not specified, default is today
    - If number of nights is not specified, default is 3
  - Beds can be filtered by arrival date
    - Arrival date must not be in the past
    - Minimal capacity must be specified
  - Beds can be filtered by number of nights
    - Number of nights must be between 0 and 90
    - Minimal capacity must be specified
    
## E2S1 (AUR)
- When cancelling a booking (`POST /beds/:bedNumber/bookings/:bookingNumber/cancel`)
  - Cancellation must be at least 24 hours prior to booking
  - Booking must not already be cancelled
  - If cancellation is at least 7 days prior to booking
    - Two `STAY_CANCELLED` transactions are added to booking : 
      - Airbnb -> Tenant
        - Total : Full booking price
        - Timestamp : At moment of cancellation
      - Owner -> Airbnb
        - Total : Full booking price
        - Timestamp : At the end of the last day of booking
  - If cancellation is not at least 7 days prior to booking
    - Three `STAY_CANCELLED` transactions are added to booking : 
      - Airbnb -> Tenant
        - Total : Half booking price
        - Timestamp : At moment of cancellation
      - Airbnb -> Owner
        - Total : Half booking price
        - Timestamp : At moment of cancellation
      - Owner -> Airbnb
        - Total : Full booking price
        - Timestamp : At the end of the last day of booking
    - If the total cannot be split even, the greater half is sent to owner
- When getting a booking (`GET /beds/:bedNumber/bookings/:bookingNumber`)
  - Status must be specified in response

## E3S1 (ALC)
- When adding a bed (`POST /beds`)
  - Lodging mode can be specified
    - Lodging mode is optional
    - Default is private
- When getting all beds (`GET /beds`)
  - Lodging mode must be specified in response
- When getting a bed (`GET /beds/:bedNumber`)
  - Lodging mode must be specified in response

## E3S2 (FLH)
- When getting all beds (`GET /beds`)
  - Beds can be filtered by lodging mode
  
## E3S3 (RSC)
- When adding a booking (`POST /beds/:bedNumber/bookings`)
  - If bed has a private lodging mode
      - Colony size not must be specified
      - Booking price calculation is same as before
  - If bed has a cohabitation lodging mode
      - Colony size must be specified
      - Colony size must be more than 0
        - Colony size must not surpass bed capacity minus booked colony sizes for each day of booking
      - Booking total (before rebate calculation) will be :
        - `(colonySize/bedCapacity) * packagePrice * numberOfNights`
