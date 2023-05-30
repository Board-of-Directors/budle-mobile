package fit.budle.dao.business

import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.request.response.business.EstCreationResponse
import fit.budle.request.response.business.GetEstListResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface OwnerMainDAO {
    @GET("user/establishments")
    suspend fun getEstablishmentList(@Query("id") id: Int): Response<GetEstListResponse>

    @PUT("establishment")
    suspend fun putEstablishment(
        @Query("establishmentDto") establishmentDto: NewEstablishmentDto,
        @Query("establishmentId") establishmentId: Int,
    ): Response<EstCreationResponse.PutEstablishmentResponse>

    @DELETE("establishment")
    suspend fun deleteEstablishment(@Query("establishmentId") establishmentId: Int)
            : Response<EstCreationResponse.DeleteEstablishmentResponse>
}