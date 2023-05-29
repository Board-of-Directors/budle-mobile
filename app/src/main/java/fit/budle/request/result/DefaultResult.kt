package fit.budle.request.result

sealed interface DefaultResult {
    data class Success(val result: Boolean?, val exception: String?) : DefaultResult
    data class Failure(val exception: String?) : DefaultResult
}
