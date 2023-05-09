package fit.budle.dao

import fit.budle.dto.response.DefaultResponse
import fit.budle.dto.response.EstablishmentResponse
import fit.budle.dto.response.OrderCreateResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface OrderCreateDAO {
    @POST("order")
    suspend fun postOrder(@Body requestBody: RequestBody): DefaultResponse.DefaultBooleanResponse

    @GET("establishment/time")
    suspend fun getEstablishmentTime(
        @Query("establishmentId") establishmentId: Long?,
    ): OrderCreateResponse.ScheduleDayArrayResponse
}
