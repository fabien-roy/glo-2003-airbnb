# Air Bed and Bug (Airbnb)

[![Build](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Airbnb%20CI/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Airbnb+CI%22)
[![Deploy](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Airbnb%20CD/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Airbnb+CD%22)
[![Codecov](https://codecov.io/gh/glo2003/glo2003-h2020-eq08/branch/master/graph/badge.svg?token=SNtgyhzwAR)](https://codecov.io/gh/glo2003/glo2003-h2020-eq08)
[![Dependabot](https://badgen.net/badge/Dependabot/enabled/green?icon=dependabot)](https://dependabot.com/)

This is our project for course GLO-2003 at Laval University. We are team 8.

Docs about what must be achieved by this app is documented in [/docs](/docs).

Our project is hosted on [https://glo2003-h2020-eq08.herokuapp.com](https://glo2003-h2020-eq08.herokuapp.com).

## How to compile

Maven is used as a built automation tool, as well as a dependency manager. To build the application, use : 

`mvn clean install`

## How to execute

To execute the application, use : 

`mvn exec:java`

The app will be running on [http://localhost:4567](http://localhost:4567).

## How to run tests

Tests are located in `src/test/java/ca/ulaval/glo2003`. They are all checked pre-commit and during the CI pipeline. Coverage report are generated using Jacoco during the report built phase.

To run unit tests, use :

`mvn surefire:test`

## How to apply code style

Code style is verified pre-commit. To apply [Google Java Code Style](https://google.github.io/styleguide/javaguide.html) throughout the source code, use : 

`mvn git-code-format:format-code -DglobPattern=**/*`

## Requests

### POST /beds

Example request body :
```{json}
{
  "ownerPublicKey": "8F0436A6FB049085B7F19AB73933973BF21276276F2EC7D122AC110BB46A3A4E"::string,
  "zipCode": "12345"::string,
  "bedType": "latex" | "memoryFoam" | "springs"::string,
  "cleaningFrequency": "weekly" | "monthly" | "annual" | "never"::string,
  "bloodTypes": [
    "O-" | "O+" | "AB-" | "AB+" | "B-" | "B+" | "A-" | "A+"
  ]::string[],
  "capacity": 234::number,
  "lodgingMode": "private" | "cohabitation"::string,
  "packages": [
    { 
      "name": "bloodthirsty" | "allYouCanDrink" | "sweetTooth"::string,
      "pricePerNight": 0.00::number
    }
  ]::object[]
}
```

- `ownerPublicKey` must be a 64 characters long hexadecimal string.
- `zipCode` must be a valid 5-digit US zip code.
- `bloodTypes` must be a non-empty array.
- `capacity` must a positive integer not exceeding bed type's maximal capacity.
- `lodgingMode` is optional. Default is `"private"`.
- `packages` must be a non-empty array with no duplicate package names.
- `package.pricePerNight` must be a positive number with not more than 2 decimals.

Returns `Headers Location: /beds/:bedNumber`

### GET /beds/:bedNumber

Returns formatted bed.

### GET /beds

Optional query parameters are : 

- `package` ("bloodthirsty" | "allYouCanDrink" | "sweetTooth")
- `bedType` ("latex " | "memoryFoam" | "springs")
- `cleaningFreq` ("never" | "weekly" | "monthly" | "annual")
- `bloodTypes` ("O-" | "O+" | "A-" | "A+" | "B-" | "B+" | "AB-" | "AB+"  (array))
- `minCapacity` (positive)
- `arrivalDate` (YYYY-MM-DD, in future, need minCapacity, default is now))
- `numberOfNights` (positive, need minCapacity, default is 3)
- `origin` (valid 5-digit US zip code)
- `maxDistance` (positive, in kilometers, need origin, default is 10)

Returns filtered formatted beds.

### POST /beds/:bedNumber/bookings

Example request body :
```{json}
{
  "tenantPublicKey": "72001343BA93508E74E3BFFA68593C2016D0434CF0AA76CB3DF64F93170D60EC"::string,
  "arrivalDate": "2020-05-21"::string,
  "numberOfNights": 3::number,
  "package": "allYouCanDrink"::string,
  "colonySize": 20::number
}
```

- `tenantPublicKey` must be a 64 characters long hexadecimal string.
- `arrivalDate` must be formatted as YYYY-MM-DD and in future.
- `numberOfNights` must be positive.
- `colonySize` is optional if bed has a private lodging mode.

Returns `Headers Location: /beds/:bedNumber/bookings/:bookingNumber`

### GET /beds/:bedNumber/bookings/:bookingNumber

Returns formatted booking.

### POST /beds/:bedNumber/bookings/:bookingNumber/cancel

Cancels bed and adds cancelation transactions.

### GET /admin/transactions

Returns formatted transactions.
