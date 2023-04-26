package fit.budle.dto.result

import fit.budle.dto.establishment.EstablishmentResponse
import fit.budle.dto.Exception

sealed interface OwnerEstResult {
    data class Success(val result: Array<EstablishmentResponse>, val exception: Exception?) : OwnerEstResult
    data class Failure(val throwable: Throwable) : OwnerEstResult
}