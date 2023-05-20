package fit.budle.dao.business

import fit.budle.dto.response.WorkerResponse
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