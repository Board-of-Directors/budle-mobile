package fit.budle.dao.customer

import fit.budle.dto.code.CodeDto
import fit.budle.dto.customer_user.RequestUser
import fit.budle.request.response.DefaultBooleanResponse
import fit.budle.request.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RegistrationDAO {

    @POST("user/register")
    suspend fun postUserRegistration(@Body requestUserDto: RequestUser): Response<DefaultBooleanResponse>

    @POST("user/login")
    suspend fun postUserLogin(@Body requestUserDto: RequestUser): Response<DefaultBooleanResponse>

    @POST("code")
    suspend fun postCode(@Body codeDto: CodeDto): Response<DefaultBooleanResponse>

    @GET("code")
    suspend fun getCode(@Query("phoneNumber") phoneNumber: String): Response<DefaultBooleanResponse>

    @GET("user/me")
    suspend fun getUser(): Response<UserResponse>
}