package com.teamoptimization

class CacheForecaster(private val delegate: BaseClient) : BaseClient {

    private var cache: AcmeForecastingClientResult? = null

    override fun acmeForecast(day: String, place: String): AcmeForecastingClientResult {
        if (cache == null) {
            cache = delegate.acmeForecast(day, place)
        }
        return cache!!
    }

}