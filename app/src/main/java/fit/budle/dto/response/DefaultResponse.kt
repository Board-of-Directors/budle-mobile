package fit.budle.dto.response

import fit.budle.dto.Exception

sealed interface DefaultResponse {
    data class DefaultBooleanResponse(val result: Boolean?, val exception: Exception?) :
        DefaultResponse
}
