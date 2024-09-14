package mobilt_java23.carl_sundberg.apiintegrationv4.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobilt_java23.carl_sundberg.apiintegrationv4.network.Asteroid
import mobilt_java23.carl_sundberg.apiintegrationv4.network.NasaApi

class AsteroidViewModel : ViewModel() {

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>> get() = _asteroids


    fun getDateAsteroids(startDate: String, endDate: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.getDateAsteroids(startDate, endDate, apiKey)
                val asteroids = response.near_earth_objects.flatMap { it.value }

                _asteroids.value = asteroids
            } catch (e: Exception) {
                _asteroids.value = emptyList()
            }
        }
    }

    fun getAsteroidsForToday(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.getTodayAsteroids(apiKey = apiKey)
                val asteroids = response.near_earth_objects.flatMap { it.value.map { asteroid ->
                    asteroid.close_approach_data.firstOrNull()

                    Asteroid(
                        id = asteroid.id,
                        name = asteroid.name,
                        absolute_magnitude_h = asteroid.absolute_magnitude_h,
                        nasa_jpl_url = asteroid.nasa_jpl_url + "&view=VOP",
                        estimated_diameter = asteroid.estimated_diameter,
                        is_potentially_hazardous_asteroid = asteroid.is_potentially_hazardous_asteroid,
                        close_approach_data = asteroid.close_approach_data
                    )
                }}
                _asteroids.value = asteroids

            } catch (e: Exception) {
                Log.e("AsteroidViewModel", "Error hämta dagens asteroider: ${e.message}")
                _asteroids.value = emptyList()
            }
        }
    }
}
