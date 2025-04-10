import com.teamoptimization.AcmeForecastClient
import com.teamoptimization.BaseClient
import com.teamoptimization.CacheForecaster
import org.http4k.client.JavaHttpClient
import org.http4k.core.Uri
import org.http4k.filter.ClientFilters

fun main(args: Array<String>) {
    if (args.size != 2) {
        throw RuntimeException("Must specify Day and Place")
    }
    val acmeForecastClient = CacheForecaster(AcmeForecastClient(ClientFilters.SetBaseUriFrom(Uri.of("https://pqjbv9i19c.execute-api.eu-west-2.amazonaws.com"))(JavaHttpClient())))
    printForecast(args[0], args[1], acmeForecastClient)
    printForecast(args[0], args[1], acmeForecastClient)
    printForecast(args[0], args[1], acmeForecastClient)
}

private fun printForecast(day: String, place: String, acmeForecastClient: BaseClient) {
    val acmeForecast = acmeForecastClient.acmeForecast(day, place)

    val emoji =
        if (acmeForecast.min.toInt() < 5) {
            "❄️"
        } else {
            if (acmeForecast.max.toInt() < 15) {
                "🧥"
            } else {
                "🔥"
            }
        }
    println(message(emoji, acmeForecast.description, acmeForecast.min.toInt(), acmeForecast.max.toInt()))
}

private fun message(emoji: String, description: String, min: Int, max: Int) =
    "$description $emoji Expect temperatures in the range $min-${max}°C."

fun moo() = "boo"
