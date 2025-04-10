package com.teamoptimization

class AcmeForecastFake : BaseClient {

    private var possibleWeatherMap: MutableMap<String, List<AcmeForecastingClientResult>> = mutableMapOf()

    init {
        possibleWeatherMap["Oxford"] = mutableListOf(
            AcmeForecastingClientResult("4", "10", "Cold and Rainy"),
            AcmeForecastingClientResult("10", "23", "Sunny"),
            AcmeForecastingClientResult("10", "15", "Stormy"),
            AcmeForecastingClientResult("-5", "0", "Snowy")
        )
        possibleWeatherMap["London"] = mutableListOf(
            AcmeForecastingClientResult("4", "25", "Stabby"),
            AcmeForecastingClientResult("0", "7", "Stinky"),
            AcmeForecastingClientResult("10", "15", "Smoggy"),
            AcmeForecastingClientResult("-5", "0", "Sleety")
        )
    }


    private var position: Int = 0

    override fun acmeForecast(day: String, place: String): AcmeForecastingClientResult {
        val weather = possibleWeatherMap[place]?.get(position)
        if (position + 1 > 3) {
            position = 0
        } else {
            position++
        }
        return weather!!
    }
}
