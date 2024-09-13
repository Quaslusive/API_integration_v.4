package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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


        asteroidViewModel.getAsteroidsForToday("V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2")  // Anropa metoden här

        asteroidViewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->

            val closestAsteroid = asteroidList.minByOrNull {
                it.closeApproachData.firstOrNull()?.missDistance?.kilometers?.toDouble() ?: Double.MAX_VALUE
            }


            closestAsteroid?.let {
                val distance = it.closeApproachData.firstOrNull()?.missDistance?.kilometers ?: "Okänt avstånd"
                closestAsteroidTextView.text = "Närmaste asteroid: ${it.name}, Avstånd: $distance km"
            } ?: run {
                closestAsteroidTextView.text = "Ingen asteroid hittad för idag."
            }
        })

        return view
    }
}
