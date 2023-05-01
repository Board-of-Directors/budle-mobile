package fit.budle.dto.response

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentResponse
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto

data class GetEstListResponse(val result: List<EstablishmentShortDto>, val exception: Exception?)