# Integration tests

On top of keeping our code with a high coverage of unit testing and practicing TDD, we use Postman to test of application in execution.

## TODO

Problems I realize while writing this : 

- Set up should have all possible attributes combination for each object type.
- Filtering (beds and reports) should test for all possible query param combination.

## Location

We keep clean example of expected requests in `ressource/postman/requests_*.json` where `*` is the collection and the environment used for those requests`**`.

Our integrations tests are in `ressource/postman/integration_tests_*.json` where `*` : is see `**`.

## Collection

```
Airbnb - Integration Tests
└───Set up
│   |   Add beds : Adds all possible beds
│   └───Add bookings : Adds all possible bookings
│   
└───Getting one bed
|   └───Valid bed number : Gets all possible beds
|   |   |   Set status to 200
|   |   |   Has no bed number
|   |   |   Get bed type
|   |   |   Get cleaning frequency
|   |   └───...
|   |   
|   └───Invalid bed number
|   |   |   Set status to 404
|   |   |   Have bed not found error
|   |   └───Have bed not found description
|   |   
|   └───Non existent bed number
|       |   Set status to 404
|       |   Have bed not found error
|       └───Have bed not found description
| 
└───Getting many beds
|   └───No query param
|   |   |   Set status to 200
|   |   |   Have bed numbers
|   |   |   Get correct bed formats
|   |   └───Get correct bed values
|   |
|   └───Bed type param
|   |   |───Valid (all bed types)
|   |   |   |   Set status to 200
|   |   |   |   Have bed numbers
|   |   |   |   Get correct bed formats
|   |   |   └───Get correct bed values
|   |   |
|   |   └───Invalid
|   |       |   Set status to 404
|   |       |   Have invalid bed type error
|   |       └───Have invalid bed type description
|   |
|   └───...
|
└───Adding one bed 
|   └───Bed type
|   |   └───Null
|   |   |   |   Set status to 404
|   |   |   |   Have invalid bed type error
|   |   |   └───Have invalid bed type description
|   |   |
|   |   └───Absent
|   |   |   |   Set status to 404
|   |   |   |   Have invalid bed type error
|   |   |   └───Have invalid bed type description
|   |   |
|   |   └───Invalid
|   |   |   |   Set status to 404
|   |   |   |   Have invalid bed type error
|   |   |   └───Have invalid bed type description
|   |   |
|   |   └───...
|   |
|   └───...
|
└───Getting one booking
|   └───Valid booking number : Gets all possible bookings
|   |   |   Set status to 200
|   |   |   Has no booking number
|   |   |   Get booking 
|   |   |   Get cleaning frequency
|   |   └───...
|   |   
|   └───Invalid booking number
|   |   |   Set status to 404
|   |   |   Have booking not found error
|   |   └───Have booking not found description
|   |   
|   └───Non existent booking number
|       |   Set status to 404
|       |   Have booking not found error
|       └───Have booking not found description
|
└───Adding one booking
|   └───Arrival date
|   |   └───Invalid format
|   |   |   |   Set status to 404
|   |   |   |   Have invalid arrival date error
|   |   |   └───Have invalid arrival date description
|   |   |
|   |   └───In the past
|   |   |   |   Set status to 404
|   |   |   |   Have arrival date in the past error
|   |   |   └───Have arrival date in the past description
|   |   |
|   |   └───...
|   |
|   └───...
|
└───Getting all transactions
|   └───No query param
|       |   Set status to 200
|       |   Get correct transaction formats
|       └───Get correct transaction values
|
└───Getting many report periods
|   └───No query param
|   |   |   Set status to 200
|   |   |   Get correct report periods formats
|   |   └───Get correct report periods values
|   |
|   └───No query param
|
└───Deleting database
    |   Set status to 200
    |   Delete all beds
    |   Delete all bookings
    |   Delete all periods
    └───Delete all transactions
```

## Structure of collection

Set up - Doing - With - Should

 - Set up : Setup of used object
   - Adds all possible combination of attributes that are valid for a given object type
   - Information about objects are saved in environment variables
 - Doing : Unit of work, what is done
 - With : Condition for test to make sense
 - Should : Assertion of integration test
   - This could be considered as the test itself, within 
   - Some tests : 
     - Check that request was valid (OK)
     - Make sure expected error was thrown
     - Confirms an object presence when getting one or many
     - Validates interactions between objects (ex : creating transactions when booking)
