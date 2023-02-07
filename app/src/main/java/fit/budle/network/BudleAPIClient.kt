package fit.budle.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object BudleAPIClient {
    private const val baseURL = "http://192.168.43.224:80/"
    private val retrofit =
        Retrofit.Builder().baseUrl(baseURL).addConverterFactory(MoshiConverterFactory.create())
    val service: BudleAPIRequests by lazy {
        retrofit.build().create(BudleAPIRequests::class.java)
    }
}
