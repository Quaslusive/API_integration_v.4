package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel

class AsteroidTodayFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_today, container, false)

        val closestAsteroidTextView: TextView = view.findViewById(R.id.closestAsteroidTextView)
        val orbitButton: Button = view.findViewById(R.id.orbitButton)

        asteroidViewModel.getAsteroidsForToday("V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2")

        asteroidViewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            // Hitta närmaste asteroid baserat på miss_distance_kilometers från close_approach_data
            val closestAsteroid = asteroidList.minByOrNull { asteroid ->
                asteroid.close_approach_data.firstOrNull()?.miss_distance?.kilometers?.toDouble() ?: Double.MAX_VALUE
            }

            // Visa asteroidens namn, avstånd och hastighet
            closestAsteroid?.let {
                val missDistance = it.close_approach_data.firstOrNull()?.miss_distance?.kilometers ?: "Okänt avstånd"
                val velocity = it.close_approach_data.firstOrNull()?.relative_velocity?.kilometers_per_second ?: "Okänd hastighet"
                val isHazardous = it.is_potentially_hazardous_asteroid
                closestAsteroidTextView.text =
                    "Närmaste asteroid: ${it.name}, Avstånd: $missDistance km, Hastighet: $velocity km/s, är det farligt?: $isHazardous"

                // Hantera knappen för att visa asteroidens omloppsbana
                val jplUrl = it.nasa_jpl_url // Hämta URL från asteroidens data
                orbitButton.setOnClickListener {
                    // Skapa ett Bundle för att skicka med URL till AsteroidOrbitFragment
                    val bundle = Bundle()
                    bundle.putString("nasa_jpl_url", jplUrl)

                    findNavController().navigate(R.id.asteroidOrbitFragment, bundle)
                }

            } ?: run {
                closestAsteroidTextView.text = "Ingen asteroid hittad för idag."
                orbitButton.isEnabled = false // Inaktivera knappen om ingen asteroid hittas
            }
        })

        return view
    }
}
