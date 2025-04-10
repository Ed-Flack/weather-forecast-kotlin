package com.teamoptimization

class CacheForecaster(private val delegate: BaseClient) : BaseClient {

    private var cache: MutableMap<String, AcmeForecastingClientResult> = mutableMapOf()

    override fun acmeForecast(day: String, place: String): AcmeForecastingClientResult {
        if (!cache.containsKey(place)) {
            cache[place] = delegate.acmeForecast(day, place)
        }
        return cache[place]!!
    }

}