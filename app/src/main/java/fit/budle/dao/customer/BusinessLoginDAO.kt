package fit.budle.dao.customer

import fit.budle.dto.customer_user.RequestUser
import fit.budle.request.response.DefaultBooleanResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BusinessLoginDAO {
    @POST("user/login")
    suspend fun postBusinessLogin(@Body requestUserDto: RequestUser): Response<DefaultBooleanResponse>
}