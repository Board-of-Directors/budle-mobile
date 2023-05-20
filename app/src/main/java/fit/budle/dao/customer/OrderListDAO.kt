package fit.budle.dao.customer

import fit.budle.dto.response.DefaultResponse
import fit.budle.dto.response.OrderListResponse
import retrofit2.http.*

interface OrderListDAO {
    @GET("order")
    suspend fun getOrder(
        @Query("userId") userId: Long,
        @Query("status") status: Int?
    ): OrderListResponse.BookingArrayResponse

    @DELETE("order")
    suspend fun deleteOrder(
        @Query("userId") userId: Long,
        @Query("orderId") orderId: Long
    ): DefaultResponse.DefaultBooleanResponse
}
