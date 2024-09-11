import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobilt_java23.carl_sundberg.apiintegrationv4.databinding.AsteroidItemBinding
import mobilt_java23.carl_sundberg.apiintegrationv4.model.Asteroid

class AsteroidAdapter(
    private val asteroids: List<Asteroid>,
    private val clickListener: (Asteroid) -> Unit
) : RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val binding = AsteroidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(asteroids[position], clickListener)
    }

    override fun getItemCount(): Int = asteroids.size

    class AsteroidViewHolder(private val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid, clickListener: (Asteroid) -> Unit) {
            binding.asteroidName.text = asteroid.name
            binding.root.setOnClickListener { clickListener(asteroid) }
        }
    }
}
