# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project state

Single-module Android app (`:app`) for the ESCOM "Desarrollo de Aplicaciones Móviles Nativas" final project: an event organizer ("Organizador de Eventos"). Package / applicationId is `com.richi_mc.proyectodamn`. Not yet a git repository. UI text, identifiers and comments are in Spanish — keep new code consistent with that.

Current stage: **UI only**. All screens exist as XML layouts with navigation and hardcoded sample data (`model/DatosEjemplo.kt`, dates relative to today). There is no persistence yet (spec allows SQLite or MySQL), and pickers, maps, contacts, Drive/Dropbox, and notifications are not wired — their buttons/fields are placeholders. `res/layout/notification_evento.xml` is a ready-made RemoteViews layout for the future notification.

## Commands

Run from the repo root with the Gradle wrapper:

```bash
./gradlew assembleDebug                 # build debug APK
./gradlew installDebug                  # build and install on connected device/emulator
./gradlew assembleRelease               # release build (R8 optimization enabled)
./gradlew lint                          # Android lint
./gradlew test                          # JVM unit tests (app/src/test)
./gradlew connectedAndroidTest          # instrumented tests (app/src/androidTest), needs device/emulator

# Single test class / method
./gradlew :app:testDebugUnitTest --tests "com.richi_mc.proyectodamn.ExampleUnitTest"
./gradlew :app:testDebugUnitTest --tests "com.richi_mc.proyectodamn.ExampleUnitTest.addition_isCorrect"
./gradlew :app:connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.richi_mc.proyectodamn.ExampleInstrumentedTest
```

## Build setup (non-obvious bits)

- **AGP 9.x with built-in Kotlin**: there is no `org.jetbrains.kotlin.android` plugin applied — AGP 9 compiles Kotlin itself. Don't add the Kotlin plugin; add Kotlin-related libraries directly.
- **Gradle 9.6**, daemon JVM toolchain 25 (auto-provisioned via foojay, see `gradle/gradle-daemon-jvm.properties`). Configuration cache is on (`gradle.properties`), so build logic must be configuration-cache compatible.
- **SDK**: `compileSdk`/`targetSdk` 37, `minSdk` 29. Java source/target 11.
- **Dependencies** are declared in the version catalog `gradle/libs.versions.toml` and referenced as `libs.*`; add new ones there rather than hardcoding coordinates. Repositories are centralized in `settings.gradle.kts` (`FAIL_ON_PROJECT_REPOS`), so don't declare repositories in module build files.
- **R8 / shrinking**: release uses the new `optimization { }` DSL instead of `minifyEnabled`/`proguardFiles`. Keep rules live in `app/src/main/keepRules/*.keep` (AGP merges every file in that directory) — there is no `proguard-rules.pro`.

## UI stack

Classic Android Views (XML layouts + ViewBinding), not Jetpack Compose. Single Activity + Fragments with the Navigation component:

- `MainActivity` hosts a `DrawerLayout` (hamburger menu, `res/menu/menu_drawer.xml`), a `MaterialToolbar`, the `NavHostFragment` (`res/navigation/nav_graph.xml`) and a `BottomNavigationView` (`res/menu/menu_bottom_nav.xml`). **Menu item ids must equal nav-graph destination ids** — that is how `setupWithNavController` routes clicks. The only non-destination item is `R.id.salir`, handled manually with `finish()`.
- Each screen is a fragment under `ui/<pantalla>/` using `Fragment(R.layout.x)` + `XBinding.bind(view)` in `onViewCreated` (no binding fields). Adapters live in `ui/adapters/`; date/time formatting (es-MX) and the status chip helper `TextView.mostrarEstatus()` live in `ui/Formato.kt`.
- Theme: `Theme.ProyectoDAMN` → `Theme.Material3.DayNight.NoActionBar` with a custom M3 palette. All colors are in `values/colors.xml` with dark-mode values in `values-night/colors.xml` (there is no night theme file; the theme references color resources). Shared view styles (`Organizador.*`) are in `values/styles.xml`; category/status/month lists are in `values/arrays.xml`.
- Edge-to-edge: `AppBarLayout` takes the top inset via `fitsSystemWindows`; `MainActivity` pads the content `LinearLayout` (`R.id.contenido`) with the bottom system-bar/IME inset, so fragments don't handle insets themselves.
