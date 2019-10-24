# Figure Hunter Android

The Android application for Figure Hunter. This allows users to view all of the different figures available.

## Contents

1. [Getting Started](#getting-started)
2. [Prerequisites](#prerequisites)
3. [Build](#build)
4. [Unit Tests](#unit-tests)
5. [Instrumented Test](#instrumented-tests)
6. [Lint](#lint)

## Getting Started

Figure Hunter Android is written in Kotlin and uses Gradle with Kotlin DSL for its build system. It follows an MVVM architecture.

The application is broken into multiple modules, based on scope and layer. The `app` module combines all modules into a buildable application.
All dependencies are included in the project from remote Maven repos as Gradle dependencies. They can be found in the `*/build.gradle.kts` files of each module.

## Prerequisites

Android Studio 3.2+

## Build

Run `gradlew assembleDebug` or `gradlew assembleRelease` to build the debug and production versions of the app, respectively. The apk file can be found at `app/build/outputs/apk/*build type*/`

## Unit Tests

Run `gradlew test` to execute all unit tests. A report with the results can be found at `*module*/build/reports/tests/`

## Instrumented Tests

Run `gradlew connectedAndroidTest` to execute all instrumented tests. **NOTE:** An emulator or physical device with Window, Transition, and Animator scale set to 0 must be connected when executing instrumented tests.
A report with the results can be found at `app/build/outputs/reports/androidTests/connected/`

## Lint
Run `gradlew lint` to execute the linter over the project. A report with the results can be found at `app/build/reports/lint-results.html`
