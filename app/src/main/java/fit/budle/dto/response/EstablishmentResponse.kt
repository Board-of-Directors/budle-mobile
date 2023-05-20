package fit.budle.dto.response

import fit.budle.dto.establishment.EstablishmentDtoArray
import fit.budle.dto.order.Booking
import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentDto
import fit.budle.dto.establishment.etsablishment_type.EstablishmentExtendedDto

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
