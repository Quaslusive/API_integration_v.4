package mobilt_java23.carl_sundberg.apiintegrationv4.network

data class AsteroidResponse(
    val near_earth_objects: Map<String, List<Asteroid>>
)

data class Asteroid(
    val id: String,
    val name: String,
    val absolute_magnitude_h: Double,
    val nasa_jpl_url: String,
    val estimated_diameter: EstimatedDiameter,
    val is_potentially_hazardous_asteroid: Boolean,
    val close_approach_data: List<CloseApproachData>
)

data class EstimatedDiameter(
    val kilometers: DiameterRange,
    val meters: DiameterRange,
    val miles: DiameterRange,
    val feet: DiameterRange
)

data class DiameterRange(
    val estimated_diameter_min: Double,
    val estimated_diameter_max: Double
)

data class CloseApproachData(
    val close_approach_date: String,
    val epoch_date_close_approach: Long,
    val relative_velocity: RelativeVelocity,
    val miss_distance: MissDistance,
    val orbiting_body: String
)

data class RelativeVelocity(
    val kilometers_per_second: String,
    val kilometers_per_hour: String,
    val miles_per_hour: String
)

data class MissDistance(
    val astronomical: String,
    val lunar: String,
    val kilometers: String,
    val miles: String
)