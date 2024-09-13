package mobilt_java23.carl_sundberg.apiintegrationv4.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobilt_java23.carl_sundberg.apiintegrationv4.network.Asteroid
import mobilt_java23.carl_sundberg.apiintegrationv4.network.NasaApi
// import mobilt_java23.carl_sundberg.apiintegrationv4.network.AsteroidDetails

class AsteroidViewModel : ViewModel() {

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>> get() = _asteroids

    private val _selectedAsteroid = MutableLiveData<Asteroid>()
    val selectedAsteroid: LiveData<Asteroid> get() = _selectedAsteroid

/*    fun getAsteroids(startDate: String, endDate: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.getAsteroids(startDate, endDate, apiKey)
                _asteroids.value = response.near_earth_objects.flatMap { it.value }
            } catch (e: Exception) {
            }
        }
    }*/

    fun getAsteroidsForToday(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.getTodayAsteroids(apiKey = apiKey)

                // Extrahera asteroider från JSON och hantera close_approach_data säkert
                val asteroids = response.near_earth_objects.flatMap { it.value.map { asteroid ->
                    val closeApproachData = asteroid.close_approach_data.firstOrNull()

                    Asteroid(
                        id = asteroid.id,
                        name = asteroid.name,
                        absolute_magnitude_h = asteroid.absolute_magnitude_h,
                        nasa_jpl_url= asteroid.nasa_jpl_url + "&view=VOP",
                        estimated_diameter = asteroid.estimated_diameter,
                        is_potentially_hazardous_asteroid = asteroid.is_potentially_hazardous_asteroid,
                        close_approach_data = asteroid.close_approach_data
                    )
                }}

                // Uppdatera LiveData med den parsed data
                _asteroids.value = asteroids

            } catch (e: Exception) {
                Log.e("AsteroidViewModel", "Error fetching asteroids: ${e.message}")
            }
        }
    }


    fun selectAsteroid(asteroid: Asteroid) {
        _selectedAsteroid.value = asteroid
    }
}
