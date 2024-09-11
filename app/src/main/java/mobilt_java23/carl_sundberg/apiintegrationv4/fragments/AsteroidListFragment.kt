package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import AsteroidAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.model.Asteroid

/**
 * A fragment representing a list of Items.
 */


class AsteroidListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_list, container, false)

        // Mock asteroid data for testing
        val asteroidList: List<Asteroid> = listOf(
            Asteroid("1", "Asteroid 2023 AB", "2023-09-11", 120.5, 34000.0, 0.01),
            Asteroid("2", "Asteroid 2023 CD", "2023-09-12", 85.3, 45000.0, 0.03)
            // Add more mock data here
        )

        // Set the adapter with clickListener
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = AsteroidAdapter(asteroidList) { asteroid ->
                    // Navigate to the details fragment using directions
                    val action =
                        AsteroidListFragmentDirections.actionToAsteroidDetailsFragment(asteroid.id)
                    findNavController().navigate(action)
                }
            }
        }
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            AsteroidListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}