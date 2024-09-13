package mobilt_java23.carl_sundberg.apiintegrationv4.recyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import mobilt_java23.carl_sundberg.apiintegrationv4.network.Asteroid

class AsteroidAdapter(
private val asteroidList: List<Asteroid>,
private val clickListener: (Asteroid) -> Unit
) : RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {


    class AsteroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.asteroid_name)
        val dateTextView: TextView = itemView.findViewById(R.id.asteroid_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.asteroid_item, parent, false)
        return AsteroidViewHolder(view)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = asteroidList[position]
        holder.nameTextView.text = asteroid.name
        holder.dateTextView.text = asteroid.close_approach_data.firstOrNull()?.close_approach_date ?: "No data"
    }

    override fun getItemCount(): Int {
        return asteroidList.size
    }
}
