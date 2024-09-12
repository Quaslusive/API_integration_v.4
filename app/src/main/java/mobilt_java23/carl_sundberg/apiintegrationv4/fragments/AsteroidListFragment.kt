package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.model.Asteroid
import mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView.AsteroidAdapter
import androidx.navigation.fragment.findNavController

class AsteroidListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_list, container, false)

        // Mock asteroid data for testing
        val asteroidList: List<Asteroid> = listOf(
            Asteroid("1", "Asteroid 2023 AB", "2023-09-11", 120.5, 34000.0, 0.01),
            Asteroid("2", "Asteroid 2023 CD", "2023-09-12", 85.3, 45000.0, 0.03)
        )

        // Hämta RecyclerView från layouten
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_asteroids)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Sätt adapter för RecyclerView
        recyclerView.adapter = AsteroidAdapter(asteroidList) { asteroid ->
            // Skapa ett Bundle och lägg till asteroid-id
            val bundle = Bundle().apply {
                putString("asteroidId", asteroid.id)
            }

            // Navigera till AsteroidDetailFragment och skicka med Bundle med argument
            findNavController().navigate(R.id.action_to_asteroidDetailFragment, bundle)
        }

        return view
    }
}
