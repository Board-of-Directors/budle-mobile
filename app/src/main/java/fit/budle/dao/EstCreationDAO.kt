package fit.budle.dao

import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.dto.establishment.etsablishment_type.EstablishmentExtendedDto
import fit.budle.dto.response.EstCreationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EstCreationDAO {

    @POST("establishment")
    suspend fun postEstablishment(@Body requestEstablishmentDto: EstablishmentDTO) : EstCreationResponse.PostEstablishmentResponse

    @GET("establishment/category")
    suspend fun getCategoryList() : EstCreationResponse.GetEstCategoryListResponse

    @GET("establishment/variants")
    suspend fun getCategoryVariantList(@Query("category") category: String) : EstCreationResponse.GetEstCategoryVariantListResponse

    @GET("establishment/tags")
    suspend fun getTagList() : EstCreationResponse.GetEstTagListResponse

}