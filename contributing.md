# Contributing to GLO-2003 (H2020) - Team 8 project


### Contributions are welcome.


## How to contribute


If you have found a bug or you have and idea for a new feature, Contributing code is a great way to give something back to open source community Before you did right into the code, there are a few guidelines that we need contributors to follow:


- Consult our contributors license agreements
- Let us know your changes by creating an clearly described issue
- Forking the project and setup a new feature branch to work in
- Go makes it very simple. See our code of conduct.
- Any significant changes should almost always be accompagning by tests.
- See our code coverage
- Do your best to have good commit message for yours changes
- Push the commit to your fork and submit a pull request by one pull request per issue.


## How to built


Maven Is use as built automation tool. To start the application, use mvn clean install + mvn exec:java

Code format: `mvn git-code-format:format-code-DglobPattern=**/*`


## How to test


Running Terminal command `mvn verify` from the root folder.
To run unit tests, use `mvn surefire:test`.
For integrating test use `failsafe:integration-test`.
Coverage report are generated using `Jacoco` during the report built phase.



## Contributors:

- vince2848
- ExiledNarwal28
- bigpkd
- sandrasandra0
- hugeflower 
- Arowwa
- Flyingwhalez

