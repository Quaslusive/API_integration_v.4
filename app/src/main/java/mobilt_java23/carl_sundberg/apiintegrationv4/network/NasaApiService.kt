package mobilt_java23.carl_sundberg.apiintegrationv4.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val apiKey = "V8rm0v9dfXt821mwqXI26TMeRn0x2hFlX970nmY2"
const val BASE_URL = "https://api.nasa.gov/neo/rest/v1/"


interface NasaApiService {

    @GET("feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = mobilt_java23.carl_sundberg.apiintegrationv4.network.apiKey
    ): AsteroidResponse


    @GET("feed/today")
    suspend fun getTodayAsteroids(
        @Query("detailed") detailed: Boolean = true,
        @Query("api_key") apiKey: String = mobilt_java23.carl_sundberg.apiintegrationv4.network.apiKey
    ): AsteroidResponse

    @GET("neo/browse")
    suspend fun browseNearEarthObjects(
        @Query("api_key") apiKey: String
    ): AsteroidResponse

 /*   @GET("neo/{id}")
    suspend fun getAsteroidDetails(
        @Path("id") asteroidId: String,
        @Query("api_key") apiKey: String = mobilt_java23.carl_sundberg.apiintegrationv4.network.apiKey
    ): AsteroidDetails*/
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
