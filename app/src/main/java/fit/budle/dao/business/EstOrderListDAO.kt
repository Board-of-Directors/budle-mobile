package fit.budle.dao.business

import fit.budle.dto.response.EstOrderResponse
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface EstOrderListDAO {

    @GET("establishment/order")
    suspend fun getOrderList(
        @Query("establishmentId") establishmentId: Int,
        @Query("status") status: Int,
    ): EstOrderResponse.GetEstOrderListResponse

    @PUT("establishment/order/status")
    suspend fun putOrderStatus(
        @Query("establishmentId") establishmentId: Int,
        @Query("orderId") orderId: Int,
        @Query("status") status: Int,
    ): EstOrderResponse.PutEstOrderStatusResponse

}