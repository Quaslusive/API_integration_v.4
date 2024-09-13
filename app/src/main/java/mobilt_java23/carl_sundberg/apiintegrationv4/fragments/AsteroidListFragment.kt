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
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel
import mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView.AsteroidAdapter

class AsteroidListFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_list, container, false)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_asteroids)
        recyclerView.layoutManager = LinearLayoutManager(context)


        asteroidViewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            recyclerView.adapter = AsteroidAdapter(asteroidList) { asteroid ->
                // När en asteroid klickas, välj den i ViewModel
                asteroidViewModel.selectAsteroid(asteroid)


                findNavController().navigate(R.id.asteroidDetailFragment)
            }
        })


     //   asteroidViewModel.getAsteroids("2023-09-01", "2023-09-30", "V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2")

        return view
    }
}
