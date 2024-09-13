package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.viewModel.AsteroidViewModel

class AsteroidDetailFragment : Fragment() {

    private val asteroidViewModel: AsteroidViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_detail, container, false)

        // Hämta TextView för att visa asteroidens detaljer
        val textView: TextView = view.findViewById(R.id.asteroid_detail_text)

        // Observera den valda asteroiden från ViewModel
        asteroidViewModel.selectedAsteroid.observe(viewLifecycleOwner, Observer { asteroid ->
            // Uppdatera UI med asteroidens detaljer, använd rätt fält
            textView.text = "Asteroid: ${asteroid.name}\nHastighet: ${asteroid.velocity}"
        })

        return view
    }
}
