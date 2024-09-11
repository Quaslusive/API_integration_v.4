package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mobilt_java23.carl_sundberg.apiintegrationv4.R

class AsteroidDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_detail, container, false)

        // Hämta asteroid-id från argumenten
        val args = AsteroidDetailFragmentArgs.fromBundle(requireArguments())
        val asteroidId = args.asteroidId

        // Använd asteroidId för att hämta och visa asteroidens detaljer

        return view
    }
}