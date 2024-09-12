package mobilt_java23.carl_sundberg.apiintegrationv4.network
data class AsteroidResponse(
    val near_earth_objects: Map<String, List<Asteroid>>
)

data class Asteroid(

    val id: String,
    val name: String,
    val closeApproachDate: String,
    val diameter: Double,   // Se till att egenskapen heter "diameter" eller motsvarande
    val velocity: Double,
    val distanceFromEarth: Double
//    val id: String,
//    val name: String,
//    val closeApproachDate: String,
//    val diameter: Double,
//    val velocity: Double,
//    val distance: Double

//    val id: String,
//    val name: String,
//    val estimated_diameter: Diameter,
//    val close_approach_data: List<CloseApproachData>
)

data class Diameter(
    val kilometers: Kilometers
)

data class Kilometers(
    val estimated_diameter_min: Double,
    val estimated_diameter_max: Double
)

data class CloseApproachData(
    val close_approach_date: String,
    val relative_velocity: Velocity,
    val miss_distance: MissDistance
)

data class Velocity(
    val kilometers_per_hour: String
)

data class MissDistance(
    val kilometers: String
)
