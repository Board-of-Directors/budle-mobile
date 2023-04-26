package fit.budle.dao

import fit.budle.dto.response.WorkerResponse
import fit.budle.dto.worker.Worker
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface WorkerDAO {

    @GET("worker")
    suspend fun getWorkerArray(@Query("establishmentId") establishmentId: Long): WorkerResponse.GetWorkerArrayResponse

    @PUT("worker")
    suspend fun putWorker(
        @Query("establishmentId") establishmentId: Long,
        @Query("phoneNumber") phoneNumber: String,
    ) : WorkerResponse.PutWorkerResponse

    @DELETE("worker")
    suspend fun deleteWorker(
        @Query("establishmentId") establishmentId: Long,
        @Query("workerId") workerId: Long
    ) : WorkerResponse.DeleteWorkerResponse

}