# Compose Multiplatform Template

[![Code Checks](https://github.com/racka98/ComposeMultiplatformTemplate/actions/workflows/code-check-pipeline.yml/badge.svg)](https://github.com/racka98/ComposeMultiplatformTemplate/actions/workflows/code-check-pipeline.yml) [![Build Pipeline](https://github.com/racka98/ComposeMultiplatformTemplate/actions/workflows/ci-cd-pipeline.yml/badge.svg)](https://github.com/racka98/ComposeMultiplatformTemplate/actions/workflows/ci-cd-pipeline.yml)

This is a Kotlin Multiplatform project targeting Android, Desktop, iOS (to be added).

## Folder Structure

* `/composeApp` contains the Compose Multiplatform code and is the starting point for targets.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Android Context for the Android parts of your Kotlin app,
      `androidMain` would be the right folder for such calls.

* `/common` contains all the common code and is a starting template for any modules that you may add
  to this project.

* `/tooling` contains all the necessary tools you would need during build
  It contains the following:
    - `/checks` contains [detekt](https://github.com/detekt/detekt) configuration files
    - `/desktop` contains the icons needed for building an installer for desktop targets
    - `/proguard-config` contains proguard configuration files for both desktop and android
    - `/plugins` contains custom gradle convention plugins that simplify module configuration

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.
  It needs to be set up for use.

## Project Setup

### Understanding `/tooling/plugins`

This contains all the code for the custom plugins used in this project.
These plugins help with `build.gradle.kts` boilerplate code that can be annoying to configure

Their uses are as follows:

- `ComposeMultiplatformAppPlugin` used to configure the main app module `composeApp`.
  It contains code for android setup, Compose Multiplatform and the necessary libraries already
  configured
- `ComposeMultiplatformLibPlugin` used to configure all library sub-modules that will also contain
  Compose code
  Necessary libraries already installed too.
- `KotlinMultiplatformLibPlugin` used to configure all library sub-modules.
  Configured for `koin` and basic Android core libraries
- `DetektConventionPlugin` used to configure `detekt` code analysis in the project
  level `build.gradle.kts`

### Detekt

To run detekt for code analysis and formatting you use the command `./gradlew detekt`
or `gradle detekt`

### Name changes

There are couple of names you need to change to conform with your own project.

1. Project level `settings.gradle.kts`

- Rename `rootProject.name` to your Project name

2. `Versions` inside `/tooling/plugins/packages-name/extensions`

- Edit the `PACKAGE_NAME` to your desired one. It is important that you change this

3. `build.gradle.kts` inside `/common`

- Change android `namespace` to your own convention

4. `/common/src` and `/composeApp/src` package names

- You should change the package names inside each source folder. Eg. `work.racka.template` to
  something of your own
- You should use the Refactor tool of the IDE so the changes are applied everywhere necessary

## Github Actions

This project contains Github Actions set up in `/.github/workflows`

- `code-check-pipeline.yml` contains code for running `detekt` for code analysis.
  You can also set up other linters and unit tests inside this file
- `ci-cd-pipeline.yml` contains code for CI/CD integrations.
  You make sure you have provided the required secrets for the signing stage of the Android app.
  The secrets needed include `KEYSTORE_FILE`, `KEYSTORE_PASSWORD`, `KEY_ALIAS` and `KEY_PASSWORD`.
  Learn more about adding these keys from [here](ilharp/sign-android-release@v1)
  and [here](https://docs.github.com/en/actions/security-for-github-actions/security-guides/using-secrets-in-github-actions#creating-secrets-for-a-repository)

# Contributing

- See [CONTRIBUTING](/CONTRIBUTING.md)

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…