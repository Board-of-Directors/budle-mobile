package fit.budle.dao.customer

import fit.budle.request.response.DefaultBooleanResponse
import fit.budle.request.response.customer.OrderCreateResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface OrderCreateDAO {
    @POST("order")
    suspend fun postOrder(@Body requestBody: RequestBody): DefaultBooleanResponse

    @GET("establishment/time")
    suspend fun getEstablishmentTime(
        @Query("establishmentId") establishmentId: Long?,
    ): OrderCreateResponse.ScheduleDayArrayResponse
}
