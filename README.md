# 🌤 Weather App — Jetpack Compose

A modern Android weather app built with Jetpack Compose and the OpenWeatherMap API.

## 📸 Screenshots

| Home Screen | Weather Result |
|-------------|----------------|
| <img width="322" height="678" alt="image" src="https://github.com/user-attachments/assets/0add589d-194b-423e-8db3-b8be0794cc3a" /> | <img width="322" height="678" alt="image" src="https://github.com/user-attachments/assets/43a0aff4-c279-4805-a083-2cfa04e90bf4" /> |

## ✨ Features

- Search weather by city name
- Displays temperature, feels like, humidity and description
- Loading indicator during network requests
- Error handling for invalid city or no internet
- Search on button tap or keyboard action

## 🛠 Tech Stack

- **Kotlin**
- **Jetpack Compose** — declarative UI
- **Material 3** — design system
- **Retrofit2** — HTTP requests
- **Gson** — JSON parsing
- **ViewModel + StateFlow** — MVVM architecture
- **Coroutines** — asynchronous operations
- **OpenWeatherMap API**

## 📁 Project Structure

```
app/src/main/
└── java/com/m1nkaa/weatherappcompose/
    ├── MainActivity.kt        # Entry point and WeatherScreen UI
    ├── WeatherApi.kt          # Retrofit interface
    ├── WeatherResponse.kt     # Data models
    └── WeatherViewModel.kt    # Business logic with sealed state
```

## ⚙️ Setup

1. Register at [openweathermap.org](https://openweathermap.org) and get a free API key
2. In `MainActivity.kt`, replace the placeholder:
```kotlin
private val API_KEY = "YOUR_API_KEY_HERE"
```

## ▶️ Getting Started

1. Clone the repository
```bash
git clone https://github.com/M1nkaa/WeatherAppCompose.git
```
2. Open in **Android Studio**
3. Add your API key to `MainActivity.kt`
4. Run on an emulator or real device (API 24+)

## 📚 What I Learned

- **Jetpack Compose** UI with `Scaffold`, `Card`, `OutlinedTextField`
- **Sealed class** for representing UI state (Idle, Loading, Success, Error)
- **MVVM** architecture with `ViewModel` + `StateFlow`
- Networking with **Retrofit** and **Gson**
- **Coroutines** for async API calls
- Git flow: branches, commits, tags, rebase
