package fit.budle.dto.response

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentDtoResponseDto
import fit.budle.dto.tag.standard.TagResponse

sealed interface EstCreationResponse {
    data class PostEstablishmentResponse(val result: EstablishmentDtoResponseDto?, val exception: Exception?) : EstCreationResponse
    data class GetEstCategoryListResponse(val result: List<String>, val exception: Exception?) : EstCreationResponse
    data class GetEstCategoryVariantListResponse(val result: List<String>, val exception: Exception?) : EstCreationResponse
    data class GetEstTagListResponse(val result: List<TagResponse>, val exception: Exception?) : EstCreationResponse
}
