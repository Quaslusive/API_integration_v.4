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

    // LiveData for asteroids
    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>> get() = _asteroids

    // LiveData for selected asteroid
    private val _selectedAsteroid = MutableLiveData<Asteroid>()
    val selectedAsteroid: LiveData<Asteroid> get() = _selectedAsteroid

    // LiveData for browsing NEOs
    private val _neoList = MutableLiveData<List<Asteroid>>()
    val neoList: LiveData<List<Asteroid>> get() = _neoList

    // Fetch a list of near-Earth objects from the browse endpoint
    fun browseNearEarthObjects(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.browseNearEarthObjects(apiKey)
                // Flatten the map into a single list of asteroids
                val asteroids = response.near_earth_objects.flatMap { entry ->
                    entry.value
                }
                // Assign the flattened list to _neoList
                _neoList.value = asteroids
            } catch (e: Exception) {
                Log.e("AsteroidViewModel", "Error browsing NEOs: ${e.message}")
                _neoList.value = emptyList() // Handle error by returning an empty list
            }
        }
    }


    // Fetch today's asteroids from NASA's API
    fun getAsteroidsForToday(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaApi.retrofitService.getTodayAsteroids(apiKey = apiKey)

                // Extract asteroids from JSON and handle `close_approach_data` safely
                val asteroids = response.near_earth_objects.flatMap { it.value.map { asteroid ->
                    val closeApproachData = asteroid.close_approach_data.firstOrNull()

                    Asteroid(
                        id = asteroid.id,
                        name = asteroid.name,
                        absolute_magnitude_h = asteroid.absolute_magnitude_h,
                        nasa_jpl_url = asteroid.nasa_jpl_url + "&view=VOP", // Add view parameter to the URL
                        estimated_diameter = asteroid.estimated_diameter,
                        is_potentially_hazardous_asteroid = asteroid.is_potentially_hazardous_asteroid,
                        close_approach_data = asteroid.close_approach_data
                    )
                }}

                // Update LiveData with the parsed data
                _asteroids.value = asteroids

            } catch (e: Exception) {
                Log.e("AsteroidViewModel", "Error fetching today's asteroids: ${e.message}")
                _asteroids.value = emptyList() // Handle error by returning an empty list
            }
        }
    }

    // Select an asteroid to display its details
    fun selectAsteroid(asteroid: Asteroid) {
        _selectedAsteroid.value = asteroid
    }
}
