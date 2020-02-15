# Plan for release 1

Release 1 has one epic, `Book a stay`. This epic has three stories : 

 - [AUL : Display a bed](https://hackmd.io/J7kxhchaQJyL9ktkQ-WUwg)
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
    /domain -> domain objects
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
