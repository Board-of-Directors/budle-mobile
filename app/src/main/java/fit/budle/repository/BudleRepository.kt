package fit.budle.repository

import android.util.Log
import fit.budle.model.EstablishmentResult
import fit.budle.network.BudleAPIRequests
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class BudleRepository(val budleAPIRequests: BudleAPIRequests) {
    sealed class Result {
        object LOADING : Result()
        data class Success(val result: Boolean?, val exceptionMessage: String?) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    sealed class ResultList {
        object LOADING : ResultList()
        data class Success(val result: EstablishmentResult, val exceptionMessage: String?) :
            ResultList()

        data class Failure(val throwable: Throwable) : ResultList()
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

    suspend fun checkCode(phoneNumber: String, code: String): Result {
        return try {
            val jsonObject = JSONObject()
            jsonObject.put("phoneNumber", phoneNumber)
            jsonObject.put("code", code)
            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val result = budleAPIRequests.checkCodeRequest(requestBody)
            Log.d("CHECKCODE", "SUCCESS")
            Result.Success(result = result.result, exceptionMessage = result.exception?.message)
        } catch (exception: Exception) {
            Log.e("CHECKCODE", "FAILURE")
            Result.Failure(exception)
        }
    }

    suspend fun sendUser(phoneNumber: String, password: String, name: String): Result {
        return try {
            val jsonObject = JSONObject()
            jsonObject.put("phoneNumber", phoneNumber)
            jsonObject.put("password", password)
            jsonObject.put("name", name)
            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val result = budleAPIRequests.sendUserRequest(requestBody)
            Log.d("SENDUSER", "SUCCESS")
            Result.Success(result = result.result, exceptionMessage = result.exception?.message)
        } catch (exception: Exception) {
            Log.e("SENDUSER", "FAILURE")
            Result.Failure(exception)
        }
    }

    suspend fun getEstablishmentsRequest(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean,
        hasMap: Boolean
    ): ResultList {
        return try {
            val result = budleAPIRequests.getEstablishment(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )
            Log.d("GETESTABLISHMENT", "SUCCESS")
            ResultList.Success(result = result.result, exceptionMessage = result.exception?.message)
        } catch (exception: Exception) {
            Log.e("GETESTABLISHMENT", "FAILURE")
            ResultList.Failure(exception)
        }
    }
}
