package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView.AsteroidAdapter
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel

class AsteroidBrowseFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_browse, container, false)

        // Setup RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_browse)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observe the ViewModel's neoList LiveData
        asteroidViewModel.neoList.observe(viewLifecycleOwner, Observer { asteroidList ->
            recyclerView.adapter = AsteroidAdapter(asteroidList) { asteroid ->
                // Handle click on asteroid item
                // For example: navigate to detail fragment or show a toast
            }
        })

        // Call the ViewModel to fetch data
      //  asteroidViewModel.browseNearEarthObjects("V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2")

        return view
    }
}
