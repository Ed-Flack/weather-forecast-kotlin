package com.teamoptimization

class AcmeForecastFake : BaseClient {

    val possibleWeathers: List<AcmeForecastingClientResult> = mutableListOf(
        AcmeForecastingClientResult("4", "10", "Cold and Rainy"),
        AcmeForecastingClientResult("10", "23", "Sunny"),
        AcmeForecastingClientResult("10", "15", "Stormy"),
        AcmeForecastingClientResult("-5", "0", "Snowy")
    )

    var position: Int = 0

    override fun acmeForecast(day: String, place: String): AcmeForecastingClientResult {
        val weather = possibleWeathers[position]
        if (position + 1 > 3) {
            position = 0
        } else {
            position++
        }
        return weather
    }
}
