package com.oocode

import com.teamoptimization.AcmeForecastFake
import com.teamoptimization.CacheForecaster
import kotlin.test.Test
import kotlin.test.assertEquals

class CacheTest {

    @Test
    fun `can parse known responses`() {
        val client = CacheForecaster(AcmeForecastFake())
        val response = client.acmeForecast("Monday", "Oxford")
        assertEquals( "Cold and Rainy", response.description)
        assertEquals( 4, response.min.toInt())
        assertEquals( 10, response.max.toInt())
    }

    @Test
    fun `test cache`() {
        val client = CacheForecaster(AcmeForecastFake())
        var response = client.acmeForecast("Monday", "Oxford")
        assertEquals( "Cold and Rainy", response.description)
        assertEquals( 4, response.min.toInt())
        assertEquals( 10, response.max.toInt())
        response = client.acmeForecast("Monday", "Oxford")
        assertEquals( "Cold and Rainy", response.description)
        assertEquals( 4, response.min.toInt())
        assertEquals( 10, response.max.toInt())
    }

    @Test
    fun `test cache london`() {
        val client = CacheForecaster(AcmeForecastFake())
        var response = client.acmeForecast("Tuesday", "London")
        assertEquals( "Stabby", response.description)
        assertEquals( 4, response.min.toInt())
        assertEquals( 25, response.max.toInt())
        response = client.acmeForecast("Tuesday", "London")
        assertEquals( "Stabby", response.description)
        assertEquals( 4, response.min.toInt())
        assertEquals( 25, response.max.toInt())
    }

}