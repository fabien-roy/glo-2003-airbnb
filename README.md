# Air Bed and Bug (Airbnb)

[![Build](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Airbnb%20CI/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Airbnb+CI%22)
[![Deploy](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Airbnb%20CD/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Airbnb+CD%22)
[![Codecov](https://codecov.io/gh/glo2003/glo2003-h2020-eq08/branch/master/graph/badge.svg?token=SNtgyhzwAR)](https://codecov.io/gh/glo2003/glo2003-h2020-eq08)
[![Dependabot](https://badgen.net/badge/Dependabot/enabled/green?icon=dependabot)](https://dependabot.com/)

This is our project for course GLO-2003 at Laval University. We are team 8.

Requests supported by the Airbnb API are specified on [this project's GitHub Pages](https://glo2003.github.io/glo2003-h2020-eq08/).

Documentation about what must be achieved by this app is documented in [/resources](/resources).

Our project is hosted on [https://glo2003-h2020-eq08.herokuapp.com](https://glo2003-h2020-eq08.herokuapp.com).

## Project setup

### Install dependencies and build project

Maven is used as a built automation tool, as well as a dependency manager. To build the application, use : 

`mvn clean install`

### Execute app

To execute the application, use : 

`mvn exec:java`

The app will be running on [http://localhost:4567](http://localhost:4567).

### Run tests

Tests are located in `src/test/java/ca/ulaval/glo2003`. They are all checked pre-commit and during the CI pipeline. Coverage report are generated using Jacoco during the report built phase.

To run unit tests, use :

`mvn surefire:test`

### Apply code style

Code style is verified pre-commit. To apply [Google Java Code Style](https://google.github.io/styleguide/javaguide.html) throughout the source code, use : 

`mvn git-code-format:format-code -DglobPattern=**/*`
