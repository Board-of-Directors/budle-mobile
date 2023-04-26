package fit.budle.dao

import fit.budle.dto.response.GetEstListResponse
import retrofit2.http.GET

interface OwnerMainDAO {
    @GET("establishment")
    suspend fun getEstablishmentList() : GetEstListResponse
}