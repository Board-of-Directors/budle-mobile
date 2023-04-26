package fit.budle.repository

import fit.budle.dto.worker.DeleteWorkerResult
import fit.budle.dto.worker.GetWorkerArrayResult
import fit.budle.dto.worker.PutWorkerResult

interface WorkerRepository {

    suspend fun getWorkerArray(establishmentId: Long) : GetWorkerArrayResult

    suspend fun putWorker(establishmentId: Long, phoneNumber: String) : PutWorkerResult

    suspend fun deleteWorker(establishmentId: Long, workerId: Long) : DeleteWorkerResult
}