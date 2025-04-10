package com.teamoptimization

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

class AcmeForecastClient(private val handler: HttpHandler) : BaseClient {
    override fun acmeForecast(day: String, place: String): AcmeForecastingClientResult =
        handler(
            Request(Method.GET, "/api/forecast?place=$place&day=$day")
        ).let { response ->
            if (response.status.successful) {
                AcmeForecastingClientResult.lens(response)
            } else {
                throw RuntimeException(response.toMessage())
            }
        }
}