package me.aminsaid.cityinput.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

typealias CityResponse = ArrayList<City>

@Parcelize
data class City(
    val Version: Int?=null,
    val Key: String?,
    val Type: String?=null,
    val Rank: Int?=null,
    val LocalizedName: String?,
    val Country: Country?,
    val AdministrativeArea: AdministrativeArea?=null
):Parcelable

@Parcelize
data class Country(
    val ID: String?=null,
    val LocalizedName: String?
):Parcelable

@Parcelize
data class AdministrativeArea(
    val ID: String?,
    val LocalizedName: String?
):Parcelable

val cityResponseMock: CityResponse = arrayListOf(
    City(
        Version = 1,
        Key = "12345",
        Type = "City",
        Rank = 1,
        LocalizedName = "New York",
        Country = Country(ID = "US", LocalizedName = "United States"),
        AdministrativeArea = AdministrativeArea(ID = "NY", LocalizedName = "New York")
    ),
    City(
        Version = 1,
        Key = "67890",
        Type = "City",
        Rank = 2,
        LocalizedName = "Los Angeles",
        Country = Country(ID = "US", LocalizedName = "United States"),
        AdministrativeArea = AdministrativeArea(ID = "CA", LocalizedName = "California")
    )

)

val mockCity = City(
    LocalizedName = "Cairo",
    Key = "12345",
    Country = Country(LocalizedName = "Egypt")
)
