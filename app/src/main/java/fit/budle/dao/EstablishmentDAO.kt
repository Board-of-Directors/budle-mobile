package fit.budle.dao

import fit.budle.dto.response.EstablishmentResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface EstablishmentDAO {
    @GET("establishment")
    suspend fun getEstablishment(
        @Query("establishmentId") establishmentId: Long
    ): EstablishmentResponse.EstablishmentDtoResponse

    @GET("establishment/all")
    suspend fun getEstablishmentAll(
        @Query("category") category: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("sortValue") sortValue: String?,
        @Query("name") name: String?,
        @Query("hasCardPayment") hasCardPayment: Boolean?,
        @Query("hasMap") hasMap: Boolean?
    ): EstablishmentResponse.EstablishmentDtoArrayResponse

    @GET("establishment/category")
    suspend fun getEstablishmentCategory(
    ): EstablishmentResponse.CategoriesResponse
}
