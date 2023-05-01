package fit.budle.dto.response

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.dto.establishment.etsablishment_type.EstablishmentExtendedDto
import fit.budle.dto.tag.standard.TagResponse

sealed interface EstCreationResponse {
    data class PostEstablishmentResponse(val result: EstablishmentDTO?, val exception: Exception?) : EstCreationResponse
    data class GetEstCategoryListResponse(val result: List<String>, val exception: Exception?) : EstCreationResponse
    data class GetEstCategoryVariantListResponse(val result: List<String>, val exception: Exception?) : EstCreationResponse
    data class GetEstTagListResponse(val result: List<TagResponse>, val exception: Exception?) : EstCreationResponse
}
