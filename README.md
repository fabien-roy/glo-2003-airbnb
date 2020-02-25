# GLO-2003 (H2020) - Team 8

[![Build](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Project%20Eq08%20CI/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Project+Eq08+CI%22)
[![Deployment](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Project%20Eq08%20CD/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Project+Eq08+CD%22)

This is our project for course GLO-2003 at Laval University.

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

### GET /hello

Simply returns `"Hello World"`. This is used to test if the app is correctly deployed.

### POST /beds


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
  "packages": [
    { 
      "name": "bloodthirsty" | "allYouCanDrink" | "sweetTooth"::string,
      "pricePerNight": 0.00::number
    }
  ]::object[]
}
```

If request is valid, returns "Headers `Location: /beds/:bedNumber`"

### GET /beds/:bedNumber

If bed number exists, returns formatted bed.

### GET /beds

Possible query parameters are : 

- package
- bedType
- cleaningFreq
- bloodTypes
- minCapacity

Returns filtered formatted beds.

### POST /beds/:bedNumber/bookings

```{json}
{
  "tenantPublicKey":
    "72001343BA93508E74E3BFFA68593C
     2016D0434CF0AA76CB3DF64F93170D60EC"::string,
  "arrivalDate": "2020-05-21"::string,
  "numberOfNights": 3::number,
  "package": "allYouCanDrink"::string
}
```

If request is valid, returns "Headers `Location: /beds/:bedNumber/bookings/:bookingNumber`"

### GET /beds/:bedNumber/bookings/:bookingNumber

If bed number exists and booking number exists for that bed, returns formatted booking.

### `GET /admin/transactions`

Returns formatted transactions.
