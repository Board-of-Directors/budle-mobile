package fit.budle.dto.response

import fit.budle.dto.worker.Worker
import fit.budle.dto.Exception

sealed interface WorkerResponse {
    data class GetWorkerArrayResponse(val result: List<Worker>, val exception: Exception?) : WorkerResponse
    data class DeleteWorkerResponse(val result: Worker, val exception: Exception?) : WorkerResponse
    data class PutWorkerResponse(val result: Worker?, val exception: Exception?) : WorkerResponse
}