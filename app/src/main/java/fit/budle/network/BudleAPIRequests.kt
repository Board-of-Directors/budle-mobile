package fit.budle.network

import fit.budle.model.AnswerCategories
import fit.budle.model.AnswerEstablishment
import fit.budle.model.AnswerOrders
import fit.budle.model.AnswerRegistration
import okhttp3.RequestBody
import retrofit2.http.*

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

    @GET("order")
    suspend fun getOrders(
        @Query("userId") userId: Long,
        @Query("status") status: Int?
    ) : AnswerOrders

    @POST("order")
    suspend fun postOrders(@Body requestBody: RequestBody): AnswerRegistration

    @DELETE("order")
    suspend fun deleteOrder(
        @Query("userId") userId: Long,
        @Query("orderId") orderId: Long
    ) : AnswerRegistration
}
