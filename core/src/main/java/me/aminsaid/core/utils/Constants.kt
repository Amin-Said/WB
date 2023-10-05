package me.aminsaid.core.utils

object Constants {
    const val CITY_ID_PATH = "cityID"

    //End Points
    const val WEATHER_FOR_CAST_END_POINT = "forecasts/v1/daily/5day/{$CITY_ID_PATH}"
    const val SEARCH_CITY_END_POINT = "locations/v1/cities/autocomplete"
    const val CURRENT_WEATHER_END_POINT = "currentconditions/v1/{$CITY_ID_PATH}"

    //QUERIES
    const val QUERY_API_KEY = "apikey"
    const val QUERY_CITY_NAME = "q"
    const val QUERY_METRIC = "metric"
    const val QUERY_DERAILS_ENABLED = "details"


    const val EMPTY_DATA_RESULT = "No data received from the API"
    const val SOMETHING_WENT_WRONG = "An error occurred:"
    const val API_ERROR = "API request failed with code:"


    //DataStore
    const val APP_PREFERENCES_NAME = "task_weather_app.preferences_pb"
    const val CITY_KEY = "city_key"
    const val CITY_NAME = "city_name"
    const val COUNTRY_NAME = "country_name"

}