package fit.budle.dto.response

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentResponse

data class GetEstListResponse(val result: EstablishmentList, val exception: Exception?)
data class EstablishmentList(val establishments: Array<EstablishmentResponse>, val count: Int)