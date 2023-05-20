package fit.budle.dto.response

import fit.budle.di.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.dto.Exception

data class GetEstListResponse(val result: List<EstablishmentShortDto>, val exception: Exception?)