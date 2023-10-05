package me.aminsaid.core.utils

data class TemperatureRange(
    val Minimum: Measurement?,
    val Maximum: Measurement?
)

data class Measurement(
    val Value: Double?,
    val Unit: String?,
    val UnitType: Int?=null
)

