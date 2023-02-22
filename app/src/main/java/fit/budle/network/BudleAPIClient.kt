package fit.budle.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object BudleAPIClient {
    private const val baseURL = "http://80.64.174.33:8080/"
    private val retrofit =
        Retrofit.Builder().baseUrl(baseURL).addConverterFactory(MoshiConverterFactory.create())
    val service: BudleAPIRequests by lazy {
        retrofit.build().create(BudleAPIRequests::class.java)
    }
}
