package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView.AsteroidAdapter
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel
import java.text.SimpleDateFormat
import java.util.*

class DateSelectionFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()
    private var startDate: String = ""
    private var endDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_date_selection, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_asteroids)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val startDateButton: Button = view.findViewById(R.id.startDateButton)
        val endDateButton: Button = view.findViewById(R.id.endDateButton)
        val fetchAsteroidsButton: Button = view.findViewById(R.id.fetchAsteroidsButton)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Handle start date selection
        startDateButton.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    startDate = dateFormat.format(calendar.time)
                    startDateButton.text = "Startdatum: $startDate"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Handle end date selection
        endDateButton.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    endDate = dateFormat.format(calendar.time)
                    endDateButton.text = "Slutdatum: $endDate"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Handle fetching asteroids based on selected dates
        fetchAsteroidsButton.setOnClickListener {
            if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
                asteroidViewModel.getDateAsteroids(startDate, endDate, "V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2")
            }
        }

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
