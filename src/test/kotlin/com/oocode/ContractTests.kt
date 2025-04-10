package com.oocode
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.teamoptimization.AcmeForecastClient
import org.http4k.client.JavaHttpClient
import org.http4k.core.*
import org.http4k.filter.ClientFilters
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

abstract class WeatherContractTest {
    abstract val weatherData: HttpHandler

    @Test
    fun `can parse known responses`() {
        val client = AcmeForecastClient(weatherData)
        val response = client.acmeForecast("Monday", "Oxford")
        assertNotNull(response.description)
        assertTrue(response.min.toInt() in -11..16)
        assertTrue(response.max.toInt() in 0..49)
    }

}

class WeatherTestAgainstFake : WeatherContractTest() {
    override val weatherData: HttpHandler = FakeData()

    @Test
    fun `can parse known responses fake`() {
        val client = AcmeForecastClient(weatherData)
        val response = client.acmeForecast("Monday", "Oxford")
        assertThat(response.description, equalTo("Cold and rainy"))
        assertThat(response.min, equalTo("4"))
        assertThat(response.max, equalTo("10"))
    }

    @Test
    fun `can parse known different responses fake`() {
        val client = AcmeForecastClient(weatherData)
        val response = client.acmeForecast("Tuesday", "London")
        assertThat(response.description, equalTo("Cold and rainy"))
        assertThat(response.min, equalTo("9"))
        assertThat(response.max, equalTo("13"))
    }
}

class WeatherTestIntegrationTests : WeatherContractTest() {
    private val setBaseUri =
        ClientFilters.SetBaseUriFrom(Uri.of("https://pqjbv9i19c.execute-api.eu-west-2.amazonaws.com"))
    override val weatherData: HttpHandler = setBaseUri(JavaHttpClient())
}

class FakeData : HttpHandler {
    private val data = mutableMapOf<String, String>().also {
        it["Monday Oxford"] = "{\"min\": 4, \"max\": 10, \"description\": \"Cold and rainy\"}"
        it["Tuesday London"] = "{\"min\": 9, \"max\": 13, \"description\": \"Cold and rainy\"}"
    }

    override fun invoke(request: Request) =
        routes("/api/forecast" bind Method.GET to {
            val key = it.query("day") + " " + it.query("place")
            Response(Status.OK).body(data[key].toString())
        })(request)
}
