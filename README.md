# GLO-2003 (H2020) - Team 8

[![Build](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Project%20Eq08%20CI/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Project+Eq08+CI%22)
[![Deployment](https://github.com/glo2003/glo2003-h2020-eq08/workflows/Project%20Eq08%20CD/badge.svg)](https://github.com/glo2003/glo2003-h2020-eq08/actions?query=workflow%3A%22Project+Eq08+CD%22)

This is our project for course GLO-2003 at Laval University.

Our project is hosted on [https://glo2003-h2020-eq08.herokuapp.com](https://glo2003-h2020-eq08.herokuapp.com).

## Requests

As of right now, there is a single request, `GET /hello`, which simply returns `"Hello World"`.

## How to compile

Maven is used as a built automation tool, as well as a dependency manager. To build the application, use : `mvn clean install`.

## How to execute

To execute the application, use : `mvn exec:java`.

## How to run tests

Tests are located in `src/test/java/ca/ulaval/glo2003`. They are all checked pre-commit and during the CI pipeline. Coverage report are generated using Jacoco during the report built phase.

To run unit tests, use : `mvn surefire:test`.

For integrating test, use : `mvn failsafe:integration-test`.

## How to apply code style

Code style is verified pre-commit. To apply [Google Java Code Style](https://google.github.io/styleguide/javaguide.html) throughout the source code, use : `mvn git-code-format:format-code-DglobPattern=**/*`.
