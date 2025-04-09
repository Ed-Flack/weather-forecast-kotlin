package com.teamoptimization

interface BaseClient {
    fun acmeForecast(day: String, place: String): AcmeForecastingClientResult
}