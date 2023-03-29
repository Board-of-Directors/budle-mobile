package fit.budle.repository

import android.util.Log
import fit.budle.model.Booking
import fit.budle.model.establishment.EstablishmentResponseArray
import fit.budle.network.APIRequests
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class Repository(val APIRequests: APIRequests) {
    sealed class Result {
        object LOADING : Result()
        data class Success(val result: Boolean?, val exceptionMessage: String?) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    sealed class ResultList {
        object LOADING : ResultList()
        data class Success(val result: EstablishmentResponseArray, val exceptionMessage: String?) :
            ResultList()

        data class Failure(val throwable: Throwable) : ResultList()
    }

    sealed class ResultList2 {
        object LOADING : ResultList2()
        data class Success(val result: Array<String>, val exceptionMessage: String?) :
            ResultList2()

        data class Failure(val throwable: Throwable) : ResultList2()
    }

    sealed class ResultList3 {
        object LOADING : ResultList3()
        data class Success(val result: Array<Booking>, val exceptionMessage: String?) :
            ResultList3()

        data class Failure(val throwable: Throwable) : ResultList3()
    }

    suspend fun getEstablishmentsRequest(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?
    ): ResultList {
        return try {
            val result = APIRequests.getEstablishment(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )
            Log.d("GETESTABLISHMENT", "SUCCESS")
            ResultList.Success(result = result.result, exceptionMessage = result.exception?.message)
        } catch (exception: Exception) {
            Log.e("GETESTABLISHMENT", "FAILURE")
            ResultList.Failure(exception)
        }
    }

    suspend fun getCategoriesRequest(): ResultList2 {
        return try {
            val result = APIRequests.getCategories()
            Log.d("GETCATEGORIES", "SUCCESS")
            ResultList2.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (exception: Exception) {
            Log.e("GETCATEGORIES", "FAILURE")
            ResultList2.Failure(exception)
        }
    }

    suspend fun orderRequest(
        establishmentId: Long,
        userId: Long,
        guestCount: Int,
        time: String,
        date: String
    ): Result {
        return try {
            val jsonObject = JSONObject()
            jsonObject.put("establishmentId", establishmentId)
            jsonObject.put("userId", userId)
            jsonObject.put("guestCount", guestCount)
            jsonObject.put("time", time)
            jsonObject.put("date", date)
            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val result = APIRequests.postOrders(requestBody)
            Log.d("OREDERREQUEST", "SUCCESS")
            Result.Success(result = result.result, exceptionMessage = result.exception?.message)
        } catch (exception: Exception) {
            Log.e("OREDERREQUEST", "FAILURE")
            Result.Failure(exception)
        }
    }

    suspend fun getOrders(userId: Long, status: Int?): ResultList3 {
        return try {
            val result = APIRequests.getOrders(userId, status = status)
            Log.d("GETORDERS", "SUCCESS")
            ResultList3.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (exception: Exception) {
            Log.d("GETORDERS", "FAILURE")
            ResultList3.Failure(exception)
        }
    }

    suspend fun deleteOrderFromUser(userId: Long, orderId: Long): Result {
        return try {
            val result = APIRequests.deleteOrder(userId, orderId)
            Log.d("DELETEORDER", "SUCCESS")
            Result.Success(result = result.result, exceptionMessage = result.exception?.message)
        } catch (exception: Exception) {
            Log.d("GETORDER", "FAILURE")
            Result.Failure(exception)
        }
    }
}
