package com.teamoptimization

class AcmeForecastFake : BaseClient {

    private var possibleWeatherMap: MutableMap<String, List<AcmeForecastingClientResult>> = mutableMapOf()
    private var placeWeatherPosition: MutableMap<String, Int> = mutableMapOf()

    init {
        possibleWeatherMap["Oxford"] = mutableListOf(
            AcmeForecastingClientResult("4", "10", "Cold and Rainy"),
            AcmeForecastingClientResult("10", "23", "Sunny"),
            AcmeForecastingClientResult("10", "15", "Stormy"),
            AcmeForecastingClientResult("-5", "0", "Snowy")
        )
        placeWeatherPosition["Oxford"] = 0
        possibleWeatherMap["London"] = mutableListOf(
            AcmeForecastingClientResult("4", "25", "Stabby"),
            AcmeForecastingClientResult("0", "7", "Stinky"),
            AcmeForecastingClientResult("10", "15", "Smoggy"),
            AcmeForecastingClientResult("-5", "0", "Sleety")
        )
        placeWeatherPosition["London"] = 0
    }

    override fun acmeForecast(day: String, place: String): AcmeForecastingClientResult {
        val weather = possibleWeatherMap[place]?.get(placeWeatherPosition[place]!!)
        placeWeatherPosition[place] = (placeWeatherPosition[place]!! + 1) % possibleWeatherMap.size
        return weather!!
    }
}
