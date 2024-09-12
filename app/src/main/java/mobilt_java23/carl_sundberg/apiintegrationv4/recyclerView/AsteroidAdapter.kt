package mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.network.Asteroid

class AsteroidAdapter(
    private val asteroids: List<Asteroid>,
    private val clickListener: (Asteroid) -> Unit
) : RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_asteroid_list, parent, false)
        return AsteroidViewHolder(view)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(asteroids[position], clickListener)
    }

    override fun getItemCount() = asteroids.size

    class AsteroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.asteroid_name)

        fun bind(asteroid: Asteroid, clickListener: (Asteroid) -> Unit) {
            nameTextView.text = asteroid.name
            itemView.setOnClickListener { clickListener(asteroid) }
        }
    }
}
