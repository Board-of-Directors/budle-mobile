package fit.budle.dao

import fit.budle.dto.response.EstablishmentResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface EstablishmentDAO {
    @GET("establishment")
    suspend fun getEstablishment(
        @Query("category") category: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("sortValue") sortValue: String?,
        @Query("name") name: String?,
        @Query("hasCardPayment") hasCardPayment: Boolean?,
        @Query("hasMap") hasMap: Boolean?
    ): EstablishmentResponse.AnswerEstablishment

    @GET("establishment/category")
    suspend fun getCategories(
    ): EstablishmentResponse.AnswerCategories

    @GET("order")
    suspend fun getOrders(
        @Query("userId") userId: Long,
        @Query("status") status: Int?
    ): EstablishmentResponse.AnswerOrders

    @POST("order")
    suspend fun postOrders(@Body requestBody: RequestBody): EstablishmentResponse.AnswerRegistration

    @DELETE("order")
    suspend fun deleteOrder(
        @Query("userId") userId: Long,
        @Query("orderId") orderId: Long
    ): EstablishmentResponse.AnswerRegistration
}
