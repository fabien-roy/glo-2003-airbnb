# Flows

This document lists all flows for release 3. Issues concerning new features are linked one after the other, step by step.

## `POST /beds/:bedNumber/bookings`
- `BookingResource.add(...)`
  - Maps `BookingRequest` with `BookingMapper`
  - Adds `BookingRequest` to bed with `BedService.add(...)`
    - ...
    - Add stay booked transaction with `TransactionService.addStayBooked(...)`
      - Calculate total using service fee fro `ConfigurationService.get()`
      - If `Configuration.serviceFee` is null, return same total (#x)
      - Else, return total + total * serviceFee (#x)
    - ...
  - Sets OK as response status
  - Sets header location

## `POST /admin/transactions`
- `TransactionResource.configure(...)`
  - Maps `ConfigurationRequest` with `ConfigurationMapper` (#x)
    - Deserializes `ConfigurationRequest.serviceFee` using `ServiceFeeDeserializer` (#x)
      - Throws `InvalidServiceFeeException` if not positive integer (#x)
        - Respond associated `ErrorResponse` with `InvalidServiceFeeErrorFactory` (#x)
      - Throws `OutOfBoundsServiceFeeException` if not in [0, 15] (#x)
        - Respond associated `ErrorResponse` with `OutOfBoundsServiceFeeErrorFactory` (#x)
  - Sends `ConfigurationRequest` to `ConfigurationService.configure(...)` (#x)
      - Convert `ConfigurationRequest` to `Configuration` with `ConfigurationConverter.fromRequest(...)` (#x)
        - Sets `serviceFee` (#x)
      - Gets `Configuration` from `ConfigurationRepository.get()` (#x)
        - Gets `Configuration` (#x)
      - Updates `Configuration` with `Configuration.update(Configuration)` (#x)
        - Sets `serviceFee` to new value if the updated fee is not null (#x)
      - Sets `Configuration` with `ConfigurationRepository.set(Configuration)` (#x)
        - Replaces `Configuration` with new one (#x)
  - Sets OK as response status (#x)
