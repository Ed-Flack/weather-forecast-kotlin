package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.teamoptimization.AcmeForecastClient
import com.teamoptimization.AcmeForecastingClientResult
import com.teamoptimization.acmeForecast
import moo
import org.http4k.client.JavaHttpClient
import org.http4k.core.Response
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class WeatherDataIntegrationTest {
    @Test
    fun `can parse known response`() {
        val response = AcmeForecastClient(JavaHttpClient()).acmeForecast("Monday", "Oxford")
        assertNotNull(response.description)
        assertNotNull(response.max)
        assertNotNull(response.min)
    }


    @Test
    fun `can parse known response unit test`() {
        val hardCodedJson = "{\"min\": 4, \"max\": 10, \"description\": \"Cold and rainy\"}"
        val client = AcmeForecastClient({ Response(Status.OK).body(hardCodedJson) })

        val response = client.acmeForecast("Monday", "Oxford")

        assertThat(response.description, equalTo("Cold and rainy"))
        assertThat(response.min, equalTo("4"))
        assertThat(response.max, equalTo("10"))

    }
}
