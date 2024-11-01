package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
import java.text.SimpleDateFormat
import java.util.*

class DateSelectionFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()
    private var startDate: String = ""
    private var endDate: String = ""
    private val apiKey = BuildConfig.API_KEY
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

        val startDateTv: TextView = view.findViewById(R.id.startDateTv)
        val endDateTV: TextView = view.findViewById(R.id.endDateTV)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        startDateButton.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    startDate = dateFormat.format(calendar.time)
                   startDateTv.text = "Startdatum: $startDate"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        endDateButton.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    endDate = dateFormat.format(calendar.time)
                    endDateTV.text = "Slutdatum: $endDate"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        fetchAsteroidsButton.setOnClickListener {
            if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
                asteroidViewModel.getDateAsteroids(startDate, endDate, apiKey)
            }
        }

        asteroidViewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            recyclerView.adapter = AsteroidAdapter(asteroidList) { jplUrl ->
                //  navigation till AsteroidOrbitFragment med JPL URL
                val bundle = Bundle().apply {
                    putString("nasa_jpl_url", jplUrl)
                }
                findNavController().navigate(R.id.asteroidOrbitFragment, bundle)
            }
        })

        return view
    }
}
