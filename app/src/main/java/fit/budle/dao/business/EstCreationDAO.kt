package fit.budle.dao.business

import fit.budle.di.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.response.EstCreationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EstCreationDAO {

    @POST("establishment")
    suspend fun postEstablishment(@Body requestEstablishmentDto: NewEstablishmentDto) : EstCreationResponse.PostEstablishmentResponse

    @GET("establishment/category")
    suspend fun getCategoryList() : EstCreationResponse.GetEstCategoryListResponse

    @GET("establishment/variants")
    suspend fun getCategoryVariantList(@Query("category") category: String) : EstCreationResponse.GetEstCategoryVariantListResponse

    @GET("establishment/tags")
    suspend fun getTagList() : EstCreationResponse.GetEstTagListResponse

}
