# WB APP
Weather App (Search for your city and get the current weather and the forcast weather on next days)

## Tools and Architecture
- Kotlin
- MVVM/MVI Architecture / Multi Module
- Repository pattern
- Navigation component
- Android Jetpack
- Coroutine
- Flow
- Hilt
- Lifecycle
- ViewModel
- ViewBinding
- Retrofit2 & OkHttp3
- Unit-Test using (JUnit - MockK)
- Instrmented-Test 

## Important to run this project
You need to add your api key on this file credentials.properties to be able to run the app Get it from Here: https://developer.accuweather.com/user/login 

## Notes
- I chose AccuWeather because it provides free access to city search and weather search. However, it limits the forecast to 5 days instead of the required 7 days. Nevertheless, I believe this still fulfills the required functionality.
- Applied unit test with this coverage (SearchCityViewModel: 100% - WeatherViewModel: 87%) / (Weather State Local Library: 96%) / (Core Module:50%) / (Repository and Api in Data Module/ 100%) / (Features Modules Use Cases: 100%).


## Bonus
- Applied the weather state handler as library using MavenLocal 
- Applied the instrumented test to test AppDataStore functionality 
- Applied Dark Mode  
- Appliced CI/CD on Github Actions
 

## Screenshots 

<div style="display: flex;">
    <div style="flex: 25%; padding: 5px;">
        <img src="https://i.ibb.co/QMRKL9m/Screenshot-1696494455.png" alt="ScreenShot" width="20%"/>
    </div>
    <div style="flex: 25%; padding: 5px;">
        <img src="https://i.ibb.co/GFS33zh/Screenshot-1696494463.png" alt="ScreenShot" width="20%"/>
    </div>
</div>

### 👨‍💻 www.aminsaid.me

