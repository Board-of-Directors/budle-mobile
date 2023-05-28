package fit.budle.request.response.business

import fit.budle.dto.Exception
import fit.budle.dto.establishment.Subcategory
import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.tag.standard.TagResponse

sealed interface EstCreationResponse {
    data class PostEstablishmentResponse(
        val result: NewEstablishmentDto?,
        val exception: Exception?,
    ) :
        EstCreationResponse

    data class GetEstCategoryListResponse(val result: List<String>, val exception: Exception?) :
        EstCreationResponse

    data class GetEstCategoryVariantListResponse(
        val result: Subcategory,
        val exception: Exception?,
    ) :
        EstCreationResponse

    data class GetEstTagListResponse(val result: List<TagResponse>, val exception: Exception?) :
        EstCreationResponse
}
