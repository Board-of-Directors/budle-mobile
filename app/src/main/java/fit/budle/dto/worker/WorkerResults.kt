package fit.budle.dto.worker

import fit.budle.dto.Exception

sealed class GetWorkerArrayResult {
    data class Success(val result: List<Worker>, val exception: Exception?) : GetWorkerArrayResult()
    data class Failure(val throwable: Throwable) : GetWorkerArrayResult()
}

sealed class DeleteWorkerResult {
    data class Success(val result: Worker, val exception: Exception?) : DeleteWorkerResult()
    data class Failure(val throwable: Throwable) : DeleteWorkerResult()
}

sealed class PutWorkerResult {
    data class Success(val result: Worker?, val exception: Exception?) : PutWorkerResult()
    data class Failure(val throwable: Throwable): PutWorkerResult()
}