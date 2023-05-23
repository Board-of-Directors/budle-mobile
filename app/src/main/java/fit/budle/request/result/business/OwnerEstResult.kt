package fit.budle.request.result.business

import fit.budle.dto.Exception
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto

sealed interface OwnerEstResult {
    data class Success(val result: List<EstablishmentShortDto>, val exception: Exception?) :
        OwnerEstResult

    data class Failure(val throwable: Throwable) : OwnerEstResult
}