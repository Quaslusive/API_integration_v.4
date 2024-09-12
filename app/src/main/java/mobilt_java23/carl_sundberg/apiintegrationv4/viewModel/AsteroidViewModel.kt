package mobilt_java23.carl_sundberg.apiintegrationv4.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobilt_java23.carl_sundberg.apiintegrationv4.network.NasaApi
import mobilt_java23.carl_sundberg.apiintegrationv4.network.Asteroid

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

                // Logga API-responsen för att debugga
                Log.d("AsteroidViewModel", "API response received: ${response.near_earth_objects}")

                _asteroids.value = response.near_earth_objects.flatMap { it.value }
            } catch (e: Exception) {
                // Logga om ett fel inträffar
                Log.e("AsteroidViewModel", "Error fetching asteroids: ${e.message}")
            }
        }
    }

    fun selectAsteroid(asteroid: Asteroid) {
        _selectedAsteroid.value = asteroid
    }
}
