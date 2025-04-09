package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.teamoptimization.AcmeForecastClient
import com.teamoptimization.acmeForecast
import moo
import org.http4k.client.JavaHttpClient
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class WeatherDataIntegrationTest {
    @Test
    fun `can parse known response`() {
        val response = AcmeForecastClient().acmeForecast("Monday", "Oxford")
        assertNotNull(response.description)
        assertNotNull(response.max)
        assertNotNull(response.min)
    }
}
