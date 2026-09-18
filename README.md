# CineCritix — Android

Native Android client for the [CineCritix](https://github.com/carolain3472/CineCritixApp_backend)
film and series review platform.

Kotlin · Jetpack Compose · MVVM · Retrofit · CameraX

## Tested

Unlike most coursework Android apps, this one ships with tests at both levels:

| Test | Covers |
|---|---|
| `LoginViewModelTest` | Login state and validation logic |
| `RegistroViewModelTest` | Registration form rules |
| `MainScreenNavigationTest` | Compose UI navigation between screens |
| `ScreenAssertions`, `ComposeRuleExtensions` | Shared helpers for Compose assertions |

The helper files matter as much as the tests: pulling assertions into
`ScreenAssertions` is what keeps a Compose test suite readable past the third
screen.

```bash
./gradlew test                 # unit tests
./gradlew connectedAndroidTest # instrumented Compose tests
```

## Structure

```
CineCritixAppNavigation.kt   navigation graph
CineCritixAppRouter.kt       routing
BottomNavGraph.kt            bottom bar destinations
WebService.kt                API surface
RetrofitClient.kt            HTTP client
CameraView.kt                CameraX capture for profile photos
screens/                     Login · Register · Land · Search & filter
                             Reset password · Terms and conditions
```

State is held in ViewModels and surfaced to Compose, keeping the screens free of
network and validation logic.

## Running it

Open in Android Studio and run against the API from the
[backend repository](https://github.com/carolain3472/CineCritixApp_backend).
The base URL is set in `Constant.kt`.

## Known limitations

- Base URL is a compile-time constant, so switching environments means a rebuild
- No dependency injection: `RetrofitClient` is reached directly rather than
  injected, which is what makes the ViewModels awkward to test in isolation
- No offline cache; every screen needs the network
