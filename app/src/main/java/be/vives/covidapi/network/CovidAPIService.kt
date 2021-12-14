package be.vives.covidapi.network

import be.vives.covidapi.model.CovidBase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

    private const val BASE_URL = "https://covid-193.p.rapidapi.com/"

    /**
     * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
     * full Kotlin compatibility.
     */
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
     * object.
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()



interface CovidAPIService {
    /**
     * Returns a Coroutine [List] of [MarsProperty] which can be fetched in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("x-rapidapi-host: covid-193.p.rapidapi.com", "x-rapidapi-key:3b069d8a8fmsh7336efe6df2d389p12184djsna0a3e6513f82")
    @GET("history")
    suspend fun getHistoryByCountry(@Query("country") country: String): CovidBase
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CovidApi {
    val retrofitService : CovidAPIService by lazy { retrofit.create(CovidAPIService::class.java) }
}