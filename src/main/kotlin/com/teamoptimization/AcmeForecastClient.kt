package com.teamoptimization

import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

class AcmeForecastClient(private val handler: HttpHandler) : BaseClient {

    override fun acmeForecast(day: String, place: String): AcmeForecastingClientResult =
        handler(
            Request(Method.GET, "https://pqjbv9i19c.execute-api.eu-west-2.amazonaws.com/api/forecast?place=$place&day=$day")
        ).let { response ->
            if (response.status.successful) {
                println(response)
                println(AcmeForecastingClientResult.lens(response))
                AcmeForecastingClientResult.lens(response)
            } else {
                throw RuntimeException(response.toMessage())
            }
        }
}