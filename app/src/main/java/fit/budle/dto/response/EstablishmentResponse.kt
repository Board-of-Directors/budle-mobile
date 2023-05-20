package fit.budle.dto.response

import fit.budle.di.establishment.EstablishmentDto
import fit.budle.di.establishment.EstablishmentDtoArray
import fit.budle.dto.Exception

sealed interface EstablishmentResponse {
    data class EstablishmentDtoResponse(
        val result: EstablishmentDto,
        val exception: Exception?,
    ) : EstablishmentResponse

    data class EstablishmentDtoArrayResponse(
        val result: EstablishmentDtoArray, //TODO Сделать заглушку
        val exception: Exception?,
    ) : EstablishmentResponse

    data class CategoriesResponse(val result: Array<String>, val exception: Exception?) :
        EstablishmentResponse
}
