package fit.budle.dao

import fit.budle.model.*
import okhttp3.RequestBody
import retrofit2.http.*

interface APIRequests {
    @GET("establishment")
    suspend fun getEstablishment(
        @Query("category") category: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("sortValue") sortValue: String?,
        @Query("name") name: String?,
        @Query("hasCardPayment") hasCardPayment: Boolean?,
        @Query("hasMap") hasMap: Boolean?
    ): Response.AnswerEstablishment

    @GET("establishment/category")
    suspend fun getCategories(
    ): Response.AnswerCategories

    @GET("order")
    suspend fun getOrders(
        @Query("userId") userId: Long,
        @Query("status") status: Int?
    ): Response.AnswerOrders

    @POST("order")
    suspend fun postOrders(@Body requestBody: RequestBody): Response.AnswerRegistration

    @DELETE("order")
    suspend fun deleteOrder(
        @Query("userId") userId: Long,
        @Query("orderId") orderId: Long
    ): Response.AnswerRegistration
}
