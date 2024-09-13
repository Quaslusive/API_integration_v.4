package mobilt_java23.carl_sundberg.apiintegrationv4.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobilt_java23.carl_sundberg.apiintegrationv4.network.Asteroid
import mobilt_java23.carl_sundberg.apiintegrationv4.network.NasaApi
import mobilt_java23.carl_sundberg.apiintegrationv4.network.AsteroidDetails

class AsteroidViewModel : ViewModel() {

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>> get() = _asteroids

    private val _selectedAsteroid = MutableLiveData<Asteroid>()
    val selectedAsteroid: LiveData<Asteroid> get() = _selectedAsteroid

    // Hämta asteroider för ett specifikt tidsintervall
    fun getAsteroids(startDate: String, endDate: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.getAsteroids(startDate, endDate, apiKey)
                _asteroids.value = response.near_earth_objects.flatMap { it.value }
            } catch (e: Exception) {
                // Hantera fel
            }
        }
    }

    fun getAsteroidsForToday(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.getTodayAsteroids(apiKey = apiKey)

                // Extrahera asteroider från JSON och hantera close_approach_data säkert
                val asteroids = response.near_earth_objects.flatMap { it.value.map { asteroid ->
                    Asteroid(
                        id = asteroid.id,
                        name = asteroid.name,
                        closeApproachDate = asteroid.closeApproachData?.firstOrNull()?.closeApproachDate ?: "Okänt datum",
                        estimatedDiameter = asteroid.estimatedDiameter,
                        velocity = asteroid.velocity,
                        closeApproachData = asteroid.closeApproachData ?: emptyList()  // Hantera null genom att tilldela en tom lista
                    )
                }}

                // Uppdatera LiveData med den parsed data
                _asteroids.value = asteroids

                // Logga avståndet för den närmaste asteroiden
                val closestAsteroid = asteroids.minByOrNull {
                    it.closeApproachData.firstOrNull()?.missDistance?.kilometers?.toDouble() ?: Double.MAX_VALUE
                }
                Log.d("AsteroidViewModel", "Närmaste asteroid: ${closestAsteroid?.name}, Avstånd: ${closestAsteroid?.closeApproachData?.firstOrNull()?.missDistance?.kilometers} km")

            } catch (e: Exception) {
                Log.e("AsteroidViewModel", "Error fetching asteroids: ${e.message}")
            }
        }
    }







    fun selectAsteroid(asteroid: Asteroid) {
        _selectedAsteroid.value = asteroid
    }
}
