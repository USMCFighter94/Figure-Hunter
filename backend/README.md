# Figure Hunter Backend

The backend for Figure Hunter. This acts as the api layer for the application.

## Contents

1. [Getting Started](#getting-started)
2. [Build](#build)
3. [Unit Tests](#unit-tests)
4. [Lint](#lint)

## Getting Started

The backend of Figure Hunter is written in Kotlin and uses Gradle with Kotlin DSL for its build system.

The application is written using the Ktor framework, with a PostgreSQL database. It loosely follows a MVC architecture.
All dependencies are included in the project from remote Maven repos as Gradle dependencies. They can be found in the `build.gradle.kts` file.

## Build

Run `gradlew run` to build and run the app. Then navigate to `http://localhost:8080` to see the application

## Unit Tests

Run `gradlew test` to execute all unit tests. A report with the results can be found at `build/reports/tests/`

## Lint
Run `gradlew lint` to execute the linter over the project. A report with the results can be found at `build/reports/lint-results.html`
