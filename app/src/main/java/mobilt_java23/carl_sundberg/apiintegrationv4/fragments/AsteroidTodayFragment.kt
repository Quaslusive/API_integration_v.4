package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.network.apiKey
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel
import mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView.AsteroidAdapter

class AsteroidTodayFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_today, container, false)

        // Hämta RecyclerView och sätt layoutManager och adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_asteroids)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observera dagens asteroider från ViewModel
        asteroidViewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            recyclerView.adapter = AsteroidAdapter(asteroidList) { asteroid ->
                // När en asteroid klickas, visa asteroidens detaljer
                asteroidViewModel.selectAsteroid(asteroid)
                findNavController().navigate(R.id.asteroidDetailFragment)
            }
        })

        // Hämta dagens asteroider
        asteroidViewModel.getAsteroidsForToday(apiKey)

        return view
    }
}
