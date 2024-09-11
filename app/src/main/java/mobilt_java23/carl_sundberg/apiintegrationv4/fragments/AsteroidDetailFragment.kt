package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mobilt_java23.carl_sundberg.apiintegrationv4.R

class AsteroidDetailFragment : Fragment() {

    private var asteroidId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hämta asteroidId från arguments
        asteroidId = arguments?.getString("asteroidId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_detail, container, false)

        // Använd asteroidId för att hämta och visa asteroidens detaljer
        if (asteroidId != null) {
            // Hämta asteroidens detaljer baserat på asteroidId
            // Exempel: hämta data från ViewModel eller direkt från API
        } else {
            // Hantera fallet där asteroidId är null
        }

        return view
    }
}
