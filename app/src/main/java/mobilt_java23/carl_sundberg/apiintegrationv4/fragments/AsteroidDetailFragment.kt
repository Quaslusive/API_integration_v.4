package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import mobilt_java23.carl_sundberg.apiintegrationv4.R

class AsteroidDetailFragment : Fragment() {

    private var asteroidId: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_detail, container, false)

        // Hämta asteroidId från argumenten
        asteroidId = arguments?.getString("asteroidId")

        // Visa asteroid-id i en TextView (för demoändamål)
        val textView: TextView = view.findViewById(R.id.asteroid_detail_text)
        textView.text = "Asteroid ID: $asteroidId"

        return view
    }
}
