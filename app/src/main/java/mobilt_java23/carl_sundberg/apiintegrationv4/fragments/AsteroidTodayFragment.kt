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
import mobilt_java23.carl_sundberg.apiintegrationv4.BuildConfig
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView.AsteroidAdapter
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel

class AsteroidTodayFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()
    private val apiKey = BuildConfig.API_KEY
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_today, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_asteroids_today)
        recyclerView.layoutManager = LinearLayoutManager(context)

        asteroidViewModel.getAsteroidsForToday(apiKey)

        asteroidViewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            recyclerView.adapter = AsteroidAdapter(asteroidList) { jplUrl ->
                // Handle navigation to AsteroidOrbitFragment with the JPL URL
                val bundle = Bundle().apply {
                    putString("nasa_jpl_url", jplUrl)
                }
                findNavController().navigate(R.id.asteroidOrbitFragment, bundle)
            }
        })

        return view
    }
}
