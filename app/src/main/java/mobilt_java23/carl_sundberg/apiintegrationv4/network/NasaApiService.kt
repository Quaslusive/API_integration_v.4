package mobilt_java23.carl_sundberg.apiintegrationv4.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.nasa.gov/neo/rest/v1/"


interface NasaApiService {

    @GET("feed")
    suspend fun getDateAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): AsteroidResponse

    @GET("feed/today")
    suspend fun getTodayAsteroids(
        @Query("detailed") detailed: Boolean = true,
        @Query("api_key") apiKey: String
    ): AsteroidResponse

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
