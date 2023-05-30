package fit.budle.dao.customer

import fit.budle.request.response.customer.EstablishmentResponse
import retrofit2.Response
import retrofit2.http.*

interface EstablishmentDAO {
    @GET("establishment")
    suspend fun getEstablishment(
        @Query("establishmentId") establishmentId: Long,
    ): Response<EstablishmentResponse.EstablishmentDtoResponse>

    @GET("establishment/all")
    suspend fun getEstablishmentAll(
        @Query("category") category: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("sortValue") sortValue: String?,
        @Query("name") name: String?,
        @Query("hasCardPayment") hasCardPayment: Boolean?,
        @Query("hasMap") hasMap: Boolean?,
        @Query("workingDayCount") workingDayCount: Int?,
    ): Response<EstablishmentResponse.EstablishmentDtoArrayResponse>

    @GET("establishment/category")
    suspend fun getEstablishmentCategory(
    ): Response<EstablishmentResponse.CategoriesResponse>
}
