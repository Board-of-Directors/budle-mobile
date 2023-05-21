package fit.budle.request.result

sealed class DefaultResult {
    data class Success(val result: Boolean?, val exceptionMessage: String?) : DefaultResult()
    data class Failure(val throwable: Throwable) : DefaultResult()
}
