package fit.budle.request.response.business

import fit.budle.dto.Exception
import fit.budle.dto.worker.Worker

sealed interface WorkerResponse {
    data class GetWorkerArrayResponse(val result: List<Worker>, val exception: Exception?) :
        WorkerResponse

    data class DeleteWorkerResponse(val result: Worker, val exception: Exception?) : WorkerResponse
    data class PutWorkerResponse(val result: Worker?, val exception: Exception?) : WorkerResponse
}