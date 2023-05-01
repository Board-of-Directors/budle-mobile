package fit.budle.dao

import fit.budle.dto.response.EstOrderResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface EstOrderListDAO {

    @GET("establishment/order")
    suspend fun getOrderList(
        @Query("establishmentId") establishmentId: Int,
        @Query("status") status: Int
    ) : EstOrderResponse.GetEstOrderListResponse

    @PUT("establishment/order/accept")
    suspend fun acceptOrder(
        @Query("establishmentId") establishmentId : Int,
        @Query("orderId") orderId: Int,
    ) : EstOrderResponse.AcceptEstOrderResponse

    @DELETE("establishment/order/reject")
    suspend fun rejectOrder(
        @Query("establishmentId") establishmentId: Int,
        @Query("orderId") orderId: Int
    ) : EstOrderResponse.RejectEstOrderResponse
}