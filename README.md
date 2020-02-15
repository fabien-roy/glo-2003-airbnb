# GLO-2003 (H2020) - Team 8

[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fglo2003%2Fglo2003-h2020-eq08%2Fbadge%3Ftoken%3Dff25c5f138498a6fae3e3edc3ad8b258a4422b1a&%2Fbadge&label=build&logo=none)](https://actions-badge.atrox.dev/glo2003/glo2003-h2020-eq08/goto?token=ff25c5f138498a6fae3e3edc3ad8b258a4422b1a)
![Heroku](https://heroku-badge.herokuapp.com/?app=glo2003-h2020-eq08&root=hello)

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
