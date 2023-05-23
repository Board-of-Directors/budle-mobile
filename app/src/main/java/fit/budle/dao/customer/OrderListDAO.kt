package fit.budle.dao.customer

import fit.budle.request.response.DefaultBooleanResponse
import fit.budle.request.response.customer.OrderListResponse
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
        @Query("orderId") orderId: Long,
    ): DefaultBooleanResponse
}
