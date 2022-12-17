package fit.budle.mobile.repository

import android.util.Log
import fit.budle.mobile.network.BudleAPIRequests

class BudleRepository(val budleAPIRequests: BudleAPIRequests) {
    sealed class Result {
        object LOADING : Result()
        data class Success(val result: Boolean?, val exceptionMessage: String?) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    suspend fun getCode(phoneNumber: String): Result {
        return try {
            val result = budleAPIRequests.getCodeRequest(phoneNumber)
            Log.d("GETCODE", "SUCCESS")
            Result.Success(result = result.result, exceptionMessage = result.exception?.message)
        } catch (exception: Exception) {
            Log.e("GETCODE", "FAILURE")
            Result.Failure(exception)
        }
    }
}
