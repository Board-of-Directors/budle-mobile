package fit.budle.request.response.customer

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentDto
import fit.budle.dto.establishment.EstablishmentDtoArray

sealed interface EstablishmentResponse {
    data class EstablishmentDtoResponse(
        val result: EstablishmentDto,
        val exception: Exception?,
    ) : EstablishmentResponse

    data class EstablishmentDtoArrayResponse(
        val result: EstablishmentDtoArray,
        val exception: Exception?,
    ) : EstablishmentResponse

    data class CategoriesResponse(val result: Array<String>, val exception: Exception?) :
        EstablishmentResponse
}
