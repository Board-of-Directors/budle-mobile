package fit.budle.mobile.network

import fit.budle.mobile.model.Answer
import retrofit2.http.GET
import retrofit2.http.Query

interface BudleAPIRequests {
    /*
    @POST("register")
    suspend fun sendUserRequest(@Body requestBody: RequestBody): Answer

    @POST("checkCode")
    suspend fun checkCodeRequest(@Body requestBody: RequestBody): Answer
     */

    @GET("getCode")
    suspend fun getCodeRequest(@Query("phoneNumber") phoneNumber: String?): Answer
}
