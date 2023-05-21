package fit.budle.request.result.customer

import fit.budle.dto.Exception
import okhttp3.Headers

sealed class BusinessLoginResult {
    data class Success(val result: Boolean?, val headers: Headers, val exception: Exception?) :
        BusinessLoginResult()

    data class Failure(val throwable: Throwable?) : BusinessLoginResult()
}
