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

This is the planned software architecture / file structure for release 1 : 

```
/.../glo2003
  /beds
    /domain
      /Bed.java
        - number : UUID
        - zipCode : String
        - matressType : MatressTypes
        - cleaningFrequency : CleaningFrequencies
        - bloodTypes : List<BloodTypes>
        - capacity : int
        - package : List<Packages>
        - getStars() : int
      /BedFactory.java
        - create(Bed) : Bed
          Gives correct UUID to Bed.number
      /BedRepository.java -> Interface
        - add(Bed)
        - getByNumber(UUID) : Bed
      /BloodTypes.java (enum : O-, O+, AB-, AB+, B-, B+, A-, A+)
      /CleaningFrequencies (enum : Weekly, Monthly, Yearly, Never)
      /MatressTypes.java (enum : Latex, MemoryFoam, Springs)
      /Packages.java (enum : BloodThirsty, AllYouCanDrink, SweetTooth)
    /infrastructure
      /InMemoryBedRepository.java -> Simple in memory List
    /rest
      /exceptions
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
      /mappers
        /BedMapper.java
          - fromRequest(BedRequest) : Bed
            Validates ownerPublicKey (check if base64)
            Validates zipCode has 5 numbers
            Validates bed type exists
            Validates cleaning frequency
            Validates blood type
            Validates capacity (positive and fits bed type)
            Validates packages (valid + that other packages are included when needed)
          - toResponse(Bed) : BedResponse
            Maps Bed to BedResponse
            Calculates number of stars (might turn out to be complicated)
        /BedNotFoundExceptionMapper.java
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
      /BedRequest.java -> Fits JSON from user story
      /BedResource.java
        - add(BedRequest)
          Sends BedRequest to BedService and returns correct response and location
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
         - getByNumber(String) : BedResponse
           Maps String to UUID using a mapper (our own or another)
           Gets Bed using BedRepository.getByNumber(UUID)
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
