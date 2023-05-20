package fit.budle.repository_impl.business

import android.util.Log
import fit.budle.dao.business.WorkerDAO
import fit.budle.dto.worker.DeleteWorkerResult
import fit.budle.dto.worker.GetWorkerArrayResult
import fit.budle.dto.worker.PutWorkerResult
import fit.budle.repository.business.WorkerRepository
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(
    private val workerDAO: WorkerDAO,
) : WorkerRepository {

    override suspend fun getWorkerArray(establishmentId: Long): GetWorkerArrayResult {
        return try {
            val response = workerDAO.getWorkerArray(establishmentId)
            Log.d("GET_WORKER_ARRAY", response.result.size.toString())
            GetWorkerArrayResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.e("GET_WORKER_ARRAY", e.message!!)
            GetWorkerArrayResult.Failure(e)
        }
    }

    override suspend fun putWorker(establishmentId: Long, phoneNumber: String): PutWorkerResult {
        return try {
            val result = workerDAO.putWorker(establishmentId, phoneNumber)
            Log.d("PUT_WORKER", "SUCCESS")
            PutWorkerResult.Success(
                result = result.result,
                exception = result.exception
            )
        } catch (e: Throwable) {
            Log.e("PUT_WORKER", e.message!!)
            PutWorkerResult.Failure(e)
        }
    }

    override suspend fun deleteWorker(establishmentId: Long, workerId: Long): DeleteWorkerResult {
        return try {
            val result = workerDAO.deleteWorker(establishmentId, workerId)
            Log.d("DELETE_WORKER", "SUCCESS")
            DeleteWorkerResult.Success(
                result = result.result,
                exception = result.exception
            )
        } catch (e: Throwable) {
            Log.e("DELETE_WORKER", e.message!!)
            DeleteWorkerResult.Failure(e)
        }
    }
}