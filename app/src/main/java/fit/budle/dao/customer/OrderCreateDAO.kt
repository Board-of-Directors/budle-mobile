package fit.budle.dao.customer

import fit.budle.dto.order.RequestOrderDto
import fit.budle.request.response.DefaultBooleanResponse
import fit.budle.request.response.customer.GetEstTimeResponse
import retrofit2.Response
import retrofit2.http.*

interface OrderCreateDAO {
    @POST("order")
    suspend fun postOrder(@Body requestOrderDto: RequestOrderDto): Response<DefaultBooleanResponse>

    @GET("establishment/time")
    suspend fun getEstablishmentTime(@Query("establishmentId") establishmentId: Long?)
            : Response<GetEstTimeResponse>
}
