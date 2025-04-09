import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.teamoptimization.AcmeForecastClient
import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status
import org.junit.jupiter.api.Test

abstract class WeatherContractTest {
    abstract val weatherData: HttpHandler

    @Test
    fun `can parse known responses`() {
        val client = AcmeForecastClient(weatherData)
        val response = client.acmeForecast("Monday", "Oxford")
        assertThat(response.description, equalTo("Cold and rainy"))
        assertThat(response.min, equalTo("4"))
        assertThat(response.max, equalTo("10"))
    }

}

class WeatherTestAgainstFake : WeatherContractTest() {
    val hardCodedJson = "{\"min\": 4, \"max\": 10, \"description\": \"Cold and rainy\"}"
    override val weatherData: HttpHandler ={ Response(Status.OK).body(hardCodedJson) }
}

class WeatherTestIntegrationTests : WeatherContractTest() {

    override val weatherData: HttpHandler = JavaHttpClient()
}
