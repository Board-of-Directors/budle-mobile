package fit.budle.network

import fit.budle.model.Answer
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BudleAPIRequests {
    @POST("register")
    suspend fun sendUserRequest(@Body requestBody: RequestBody): Answer

    @POST("code")
    suspend fun checkCodeRequest(@Body requestBody: RequestBody): Answer

    @GET("code")
    suspend fun getCodeRequest(@Query("phoneNumber") phoneNumber: String?): Answer
}
