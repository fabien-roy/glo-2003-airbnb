# Plan for RUS

This document describes the flows for RUS. It is an overwrite for our original release 1 plan.

Flow for `POST /bed/:bedNumber/bookings`
- `BedResource.addBooking(...)` sends bedNumber and `BookingRequest` to `BookingService.add(...)` (#44)
  - _(inside `BookingService.add(...)`)_
  - Make a valid `Booking` using `BookingMapper.fromRequest(...)` (#56, #57, #58, #59, #60)
  - Gets `Bed` via `BedRepository` (#49)
  - `Bed.book(booking)` (#50)
  - Creates the actual `Booking` by adding it's number via `BookingFactory.create(...)` (#52)
  - Creates _both_ `Transaction` via `TransactionFactory.create(...)` (#61)
    - _(inside `TransactionFactory.create(...)`)
      - Create info for transaction (#62)
      - Calculates total for transaction (#74)
  - Saves `Bed` in `BedRepository` (#51)
  - Returns the booking number (#54)
- Setup header location and status code response (#46)

Flow for `GET /beds/:bedNumber/bookings/:bookingNumber`
- `BedResource.getBooking(...)` sends bed and booking number to `BookingService.get(...)` (#45)
- _(inside `BookingService.get(...)`)_
  - Make a valid UUID (#65)
  - Get `Bed` with `BedRepository.getByNumber(...)` (#NEWISSUE)
  - Get `Booking` from `Bed.getBookings().<get by number>` (#NEWISSUE)
  - Map `Booking` to `BookingResponse` via `BookingMapper.toResponse(...)` (#67)
  - Return `BookingResponse` (#66)
- Setup body and status code (#47)

Flow for `GET /admin/transactions`
- `TransactionResource.getAll(...)` asks `TransactionService.getAll(...)` (#69)
- _(inside `TransactionService.getAll(...)`)_
  - Get all `Bed` with `BedRepository.getAll(...)` (#71)
  - Get all `Transaction` in all `Booking` in all `Bed` (#NEWISSUE)
  - Map all `Transaction` to `TransactionResponse` via `TransactionMapper.toResponse` (#73)
  - Return that list (#NEWISSUE)
- Setup body and status code (#70)

The following lines will shortly be removed.

REMOVE #53 #63

RENDERED USELESS BUT WILL BE MERGED #72 #64
