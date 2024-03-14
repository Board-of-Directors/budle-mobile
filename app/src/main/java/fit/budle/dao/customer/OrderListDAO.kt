package fit.budle.dao.customer

import fit.budle.request.response.DefaultBooleanResponse
import fit.budle.request.response.customer.OrderListResponse
import retrofit2.http.*

interface OrderListDAO {
    @GET("user/order")
    suspend fun getOrder(
        @Query("userId") userId: Long,
        @Query("status") status: Int?
    ): OrderListResponse.BookingArrayResponse

    @DELETE("user/order")
    suspend fun deleteOrder(
        @Query("userId") userId: Long,
        @Query("orderId") orderId: Long,
    ): DefaultBooleanResponse
}
