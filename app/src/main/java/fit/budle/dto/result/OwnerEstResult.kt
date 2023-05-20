package fit.budle.dto.result

import fit.budle.di.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.dto.Exception

sealed interface OwnerEstResult {
    data class Success(val result: List<EstablishmentShortDto>, val exception: Exception?) : OwnerEstResult
    data class Failure(val throwable: Throwable) : OwnerEstResult
}