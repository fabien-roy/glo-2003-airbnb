# Plan for release 1

Release 1 has one epic, `Book a stay`. This epic has three user stories : 

 - [AUL : Add a bed](https://hackmd.io/J7kxhchaQJyL9ktkQ-WUwg)
 - [CLA : Display all beds](https://hackmd.io/jJANhBXYQxWw6nPYRVkScQ)
 - [RUS : Book a stay](https://hackmd.io/5J_oZo-bT6elSzxG3IdVEA)

The most important part of this first release is to establish a correct software architecture that will be kept throughout the project. For this, we will use the following type of architecture : 

```
/.../glo2003
  /interfaces -> General interfaces and configuration
    /configuration -> If we want to use a custom json provider, for instance
    /rest -> If needed for general exceptions, for example
      /exceptions
      /mappers
      ErrorResponse.java -> Basic response for errors, has "error" and "description"
  /example group of domain logic
    /domain -> domain objects and interfaces of repositories
    /infrastructure -> implementations of repositories
    /rest
      /exceptions -> All exceptions concerning concerning group of domain logic
      /mappers -> Exception mappers as well as mappers for responses and requests
      Resources, requests and responses of group of domain logic
    /services -> One service = one use-case, services only dispatch jobs to domain objects and other services
  CORS.java
  Main.java
  Router.java
  Server.java
  Other general config. For instance, a binder (for dependency injection) would be placed here.
```

## Software Architecture

This is the planned software architecture / file structure for release 1 : 

```
/.../glo2003
  /beds
    /bookings
      /domain
        /Booking.java
          - number : UUID
          - tenantPublicKey : Base64
          - arrivalDate : Date
          - numberOfNights : int
          - getTotal() : double
        /BookingFactory.java
          - create(Booking) : Booking
            Gives correct UUID to Booking.number
        /BookingRepository.java -> Interface
          - add(Booking)
          - getByNumber(UUID) : Booking
      /infrastructure
        /InMemoryBookingRepository.java -> Simple in memory List
      /rest
        /exceptions
          /InvalidArrivalDateException.java
          /InvalidNumberOfNightsException.java
        /mappers
          /BookingMapper.java
            - fromRequest(BookingRequest) : Booking
              Validates tenantPublicKey (check if base64)
              Validates arrivalDate (format and not in the past)
              Validates numberOfNights
              Validates package
            - toResponse(Booking) : BookingResponse
              Maps Booking to BookingResponse
              Calculates total (might turn out to be complicated)
            /InvalidArrivalDateExceptionMapper.java
          /InvalidNumberOfNightsExceptionMapper.java
        /BookingRequest.java -> Fits JSON from user story
        /BookingResource.java
          - add(BookingRequest)
            Sends BookingRequest to BookingService and returns correct response and location
          - getByNumber(String)
            Sends String to BookingService and returns correct response (BookingResponse)
        /BedResponse.java -> Fits JSON from user story
      /services
         /BookingService.java
           - add(BookingRequest) : String
             Maps BookingRequest to Booking using BookingMapper (this validates the request)
             Gets Bed using BedRepository.get(UUID)
             Bed.book(Booking)
             Creates valid Booking using BookingFactory
             Adds Booking using BookingRepository
             TransactionService.addBooking(Booking, Bed.ownerPublicKey)
             Returns Booking.number as String
           - getByNumber(String) : BookingResponse
             Maps String to UUID using a mapper (our own or another)
             Gets Booking using BookingRepository.getByNumber(UUID)
      /transactions
        /domain
          /Transaction.java
            - timestamp : DateTime
            - from : String
            - to : String
            - total : double
            - reason : TransactionReasons
          /TransactionFactory.java
            - createWithBooking(Booking, String) : Transaction
              Creates new Transaction with timestamp and information
          /TransactionReasons.java (enum : StayBooked, StayCompleted)
          /TransactionRepository.java -> Interface
            - add(Transaction)
            - getAll()
        /infrastructure
          /InMemoryTransactionRepository.java -> Simple in memory List
        /rest
          /TransactionResource.java
            - getAll()
              Sends to TransactionService.getAll()
          /TransactionResponse.java -> Fits JSON from user story
        /services
          /TransactionService.java
            - addBooking(Booking, String)
              Creates Transaction using TransactionFactory.createWithBooking(Booking, String)
              Adds Transaction using TransactionRepository.add(Transaction)
            - getAll() : List<TransactionResponse>
              Gets all Transaction using TransactionRepository.getAll()
    /domain
      /Bed.java
        - number : UUID
        - tenantOwnerKey : Base64
        - zipCode : String
        - bedType : BedTypes
        - cleaningFrequency : CleaningFrequencies
        - bloodTypes : List<BloodTypes>
        - capacity : int
        - package : List<Packages>
        - bookings : List<Booking> (This might not be a perfect solution)
        - getStars() : int
        - book(Booking) (This might not be a perfect solution)
          Validates package is for bed
          Validates tenant is not owner
          Validates bed not already booked
          Adds Booking to list of Booking
      /BedFactory.java
        - create(Bed) : Bed
          Gives correct UUID to Bed.number
      /BedMatcher.java
        - matches(Bed, Bed) : boolean
          Checks if Bed matches other Bed using attributes that are not null
          Checks for : bedType, cleaningFrequency, bloodTypes, capacity and package
          We request for a single package while Bed can have many. This should be another separated method.
      /BedRepository.java -> Interface
        - add(Bed)
        - getAll() : List<Bed>
        - getByNumber(UUID) : Bed
      /BedTypes.java (enum : Latex, MemoryFoam, Springs)
      /BloodTypes.java (enum : O-, O+, AB-, AB+, B-, B+, A-, A+)
      /CleaningFrequencies (enum : Weekly, Monthly, Yearly, Never)
      /Packages.java (enum : BloodThirsty, AllYouCanDrink, SweetTooth)
    /infrastructure
      /InMemoryBedRepository.java -> Simple in memory List
    /rest
      /exceptions
        /BookingNotAllowedException.java
        /BedAlreadyBookedException.java
        /BedNotFoundException.java
        /CantOfferAllYouCanDrinkPackageException.java
        /CantOfferSweetToothPackageException.java
        /ExceedingAccommodationCapacityException.java
        /InvalidBedTypeException.java
        /InvalidBloodTypeException.java
        /InvalidCapacityException.java
        /InvalidCleaningFrequencyException.java
        /InvalidPackageException.java
        /InvalidPublicKeyException.java
        /InvalidZipCodeException.java
        /PackageNotAvailableException.java
      /mappers
        /BedMapper.java
          - fromRequest(BedRequest) : Bed
            Validates ownerPublicKey (check if base64)
            Validates zipCode has 5 numbers
            Validates bed type exists
            Validates cleaning frequency
            Validates blood types
            Validates capacity (positive and fits bed type)
            Validates packages (valid + that other packages are included when needed)
          - fromRequestParams(params...) : Bed
            Validates bed type exists
            Validates cleaning frequency
            Validates blood types
            Validates capacity (positive)
            Validates packages (valid + that other packages are included when needed)
            Returns Bed that fits valid params
          - toResponse(Bed) : BedResponse
            Maps Bed to BedResponse
            Calculates number of stars (might turn out to be complicated)
        /BedNotFoundExceptionMapper.java
        /BedAlreadyBookedExceptionMapper.java
        /BookingNotAllowedExceptionMapper.java
        /CantOfferAllYouCanDrinkPackageExceptionMapper.java
        /CantOfferSweetToothPackageExceptionMapper.java
        /ExceedingAccommodationCapacityExceptionMapper.java
        /InvalidBedTypeExceptionMapper.java
        /InvalidBloodTypeExceptionMapper.java
        /InvalidCapacityExceptionMapper.java
        /InvalidCleaningFrequencyExceptionMapper.java
        /InvalidPackageExceptionMapper.java
        /InvalidPublicKeyExceptionMapper.java
        /InvalidZipCodeExceptionMapper.java
        /PackageNotAvailableExceptionMapper.java
      /BedRequest.java -> Fits JSON from user story
      /BedResource.java
        - add(BedRequest)
          Sends BedRequest to BedService and returns correct response and location
        - getAll(params...)
          All params are optionnal : package, bedType, cleaningFreq, bloodTypes, minCapacity
          Sends to BedService.get(params...)
        - getByNumber(String)
          Sends String to BedService and returns correct response (BedResponse)
      /BedResponse.java -> Fits JSON from user story
    /services
       /BedService.java
         - add(BedRequest) : String
           Maps BedRequest to Bed using BedMapper (this validates the request)
           Creates valid Bed using BedFactory
           Adds Bed using BedRepository
           Returns Bed.number as String
         - getAll(params...) : List<BedResponse>
           Validates params using BedMapper
           Gets all Bed using BedRepository.getAll()
           Gets all Bed that match using BedMatcher.matches(Bed, Bed)
         - getByNumber(String) : BedResponse
           Maps String to UUID using a mapper (our own or another)
           Gets Bed using BedRepository.getByNumber(UUID)
           Returns BedResponse using BedMapper
  /interfaces
    /rest
      /mappers
        CatchallExceptionMapper.java -> Catches Exception, builds 404 response
      ErrorResponse.java -> Basic response for errors, has "error" and "description"
  CORS.java
  Main.java
  Router.java
  Server.java
```

## Development

Everything will be done using TDD.
