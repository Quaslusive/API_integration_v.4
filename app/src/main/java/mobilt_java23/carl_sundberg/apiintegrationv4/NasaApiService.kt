package mobilt_java23.carl_sundberg.apiintegrationv4
import mobilt_java23.carl_sundberg.apiintegrationv4.model.AsteroidDetails
import mobilt_java23.carl_sundberg.apiintegrationv4.model.AsteroidResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

const val BASE_URL = "https://api.nasa.gov/neo/rest/v1/V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2 "

interface NasaApiService {
    @GET("feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): AsteroidResponse

    @GET("neo/{id}")
    suspend fun getAsteroidDetails(
        @Path("id") asteroidId: String,
        @Query("api_key") apiKey: String
    ): AsteroidDetails
}

object NasaApi {
    val retrofitService: NasaApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(NasaApiService::class.java)
    }
}

