package fit.budle.request.result.customer

import fit.budle.dto.Exception

sealed interface BusinessLoginResult {
    data class Success(val result: Boolean?, val exception: Exception?) :
        BusinessLoginResult

    data class Failure(val throwable: Throwable?) : BusinessLoginResult
}
