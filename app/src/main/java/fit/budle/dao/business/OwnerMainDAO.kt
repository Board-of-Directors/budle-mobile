package fit.budle.dao.business

import fit.budle.request.response.business.GetEstListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OwnerMainDAO {
    @GET("user/establishments")
    suspend fun getEstablishmentList(@Query("id") id: Int): Response<GetEstListResponse>
}