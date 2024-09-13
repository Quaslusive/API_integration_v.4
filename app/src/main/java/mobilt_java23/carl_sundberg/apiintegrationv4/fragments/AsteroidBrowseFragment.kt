package mobilt_java23.carl_sundberg.apiintegrationv4.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView.AsteroidAdapter
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel

class AsteroidBrowseFragment : Fragment() {

    private lateinit var asteroidViewModel: AsteroidViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AsteroidAdapter

    // In AsteroidBrowseFragment.kt

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_browse, container, false)

        // Correctly initialize ViewModel
        asteroidViewModel = ViewModelProvider(this).get(AsteroidViewModel::class.java)

        // Observe LiveData from the ViewModel
        asteroidViewModel.neoList.observe(viewLifecycleOwner, Observer { asteroidList ->
            // Update your RecyclerView or UI with the asteroidList here
            // Example: recyclerView.adapter = AsteroidAdapter(asteroidList)
        })

        // Fetch the NEOs by calling the method in the ViewModel
        asteroidViewModel.browseNearEarthObjects("YOUR_API_KEY")

        return view
    }

}
