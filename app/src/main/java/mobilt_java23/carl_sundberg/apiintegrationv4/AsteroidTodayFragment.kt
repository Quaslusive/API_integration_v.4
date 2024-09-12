package mobilt_java23.carl_sundberg.apiintegrationv4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class AsteroidTodayFragment : Fragment() {

    private val apiKey = "V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2"
    private val apiUrl = "https://api.nasa.gov/neo/rest/v1/feed/today?detailed=true&api_key=$apiKey"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_today, container, false)

        // Kör API-anropet för att hämta dagens asteroider
        fetchAsteroidsToday()

        return view
    }

    private fun fetchAsteroidsToday() {
        lifecycleScope.launch {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url(apiUrl)
                .build()

            try {
                // Gör API-anropet i bakgrunden
                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }

                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    responseData?.let {
                        parseAsteroidData(it)
                    }
                } else {
                    // Hantera API-fel
                    showError("API-anrop misslyckades med kod: ${response.code}")
                }
            } catch (e: Exception) {
                // Hantera nätverksfel
                showError("Nätverksfel: ${e.message}")
            }
        }
    }

    private fun parseAsteroidData(responseData: String) {
        // Parse JSON-data
        val json = JSONObject(responseData)
        val nearEarthObjects = json.getJSONObject("near_earth_objects")
        val todayAsteroids = nearEarthObjects.keys().asSequence().toList()

        // Hantera parsedata (visa den i UI eller lagra den)
        // För enkelhet kan du till exempel skriva ut antalet asteroider för idag
        activity?.runOnUiThread {
            // Visa eller använd asteroiddatan här
        }
    }

    private fun showError(message: String) {
        // Här kan du visa felmeddelanden till användaren
        activity?.runOnUiThread {
            // Visa en Toast eller uppdatera UI med ett felmeddelande
        }
    }
}
