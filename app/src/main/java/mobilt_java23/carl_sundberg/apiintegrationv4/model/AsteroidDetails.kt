package mobilt_java23.carl_sundberg.apiintegrationv4.model

data class AsteroidDetails(
    val id: String,
    val name: String,
    val nasa_jpl_url: String,
    val estimated_diameter: Diameter,
    val is_potentially_hazardous_asteroid: Boolean
)
