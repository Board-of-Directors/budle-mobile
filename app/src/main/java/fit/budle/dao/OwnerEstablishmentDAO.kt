package fit.budle.dao

import fit.budle.dto.response.EstablishmentResponse
import retrofit2.http.GET

interface OwnerEstablishmentDAO {
    @GET("establishment")
    suspend fun getEstablishmentArray() : EstablishmentResponse.GetEstablishmentArrayResponse
}