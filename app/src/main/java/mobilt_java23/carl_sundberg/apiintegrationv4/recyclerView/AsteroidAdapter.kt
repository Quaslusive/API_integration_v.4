package mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.network.Asteroid

class AsteroidAdapter(
    private val asteroidList: List<Asteroid>,
    private val onOrbitButtonClick: (String) -> Unit // Pass the JPL URL when the orbit button is clicked
) : RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.asteroid_item, parent, false)
        return AsteroidViewHolder(view)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = asteroidList[position]
        holder.bind(asteroid, onOrbitButtonClick)
    }

    override fun getItemCount(): Int = asteroidList.size

    class AsteroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val asteroidName: TextView = itemView.findViewById(R.id.asteroid_name)
        private val asteroidDistance: TextView = itemView.findViewById(R.id.asteroid_distance)
        private val absolutMagnitude: TextView = itemView.findViewById(R.id.absolute_magnitude)
        private val estimatedDiameter: TextView = itemView.findViewById(R.id.estimated_diameter)
        private val relativeVelocity: TextView = itemView.findViewById(R.id.relativeVelocity)
        private val isPotentiallyHazardousAsteroid: TextView = itemView.findViewById(R.id.isPotentiallyHazardousAsteroid)
        private val asteroidDate: TextView = itemView.findViewById(R.id.asteroid_date)
        private val orbitButton: Button = itemView.findViewById(R.id.orbitButton)

        @SuppressLint("SetTextI18n")
        fun bind(asteroid: Asteroid, onOrbitButtonClick: (String) -> Unit) {
            asteroidName.text = asteroid.name
            asteroidDistance.text = "Avstånd: ${asteroid.close_approach_data.firstOrNull()?.miss_distance?.kilometers ?: "Okänt"} km"
            absolutMagnitude.text = "Absolut magnitud: ${asteroid.absolute_magnitude_h}"
            estimatedDiameter.text = "Diameter: ${asteroid.estimated_diameter.kilometers.estimated_diameter_min} - ${asteroid.estimated_diameter.kilometers.estimated_diameter_max} km"
            relativeVelocity.text = "Hastighet: ${asteroid.close_approach_data.firstOrNull()?.relative_velocity?.kilometers_per_second ?: "Okänt"} km/s"
            asteroidDate.text = "Datum: ${asteroid.close_approach_data.firstOrNull()?.close_approach_date ?: "Okänt"}"
            isPotentiallyHazardousAsteroid.text = if (asteroid.is_potentially_hazardous_asteroid) {
                "Farlig: Ja"
            } else {
                "Farlig: Nej"
            }

            // Handle orbitButton click for each asteroid
            val jplUrl = asteroid.nasa_jpl_url
            orbitButton.setOnClickListener {
                onOrbitButtonClick(jplUrl)
            }
        }
    }
}
