package fit.budle.network

import fit.budle.model.AnswerCategories
import fit.budle.model.AnswerEstablishment
import fit.budle.model.AnswerOrder
import fit.budle.model.AnswerRegistration
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BudleAPIRequests {
    @POST("register")
    suspend fun sendUserRequest(@Body requestBody: RequestBody): AnswerRegistration

    @POST("code")
    suspend fun checkCodeRequest(@Body requestBody: RequestBody): AnswerRegistration

    @GET("code")
    suspend fun getCodeRequest(@Query("phoneNumber") phoneNumber: String?): AnswerRegistration

    @GET("establishment")
    suspend fun getEstablishment(
        @Query("category") category: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("sortValue") sortValue: String?,
        @Query("name") name: String?,
        @Query("hasCardPayment") hasCardPayment: Boolean?,
        @Query("hasMap") hasMap: Boolean?
    ): AnswerEstablishment

    @GET("establishment/category")
    suspend fun getCategories(
    ): AnswerCategories

    @POST("order")
    suspend fun getCategories(@Body requestBody: RequestBody): AnswerOrder
}
