package fit.budle.repository_impl.customer

import android.util.Log
import fit.budle.dao.customer.OrderCreateDAO
import fit.budle.dto.result.DefaultResult
import fit.budle.dto.result.OrderCreateResult
import fit.budle.repository.customer.OrderCreateRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class OrderCreateRepositoryImpl @Inject constructor(
    private val orderCreateDAO: OrderCreateDAO,
) : OrderCreateRepository {
    override suspend fun postOrder(
        establishmentId: Long,
        userId: Long,
        guestCount: Int,
        time: String,
        date: String,
        spotId: Int?
    ): DefaultResult {
        return try {
            val jsonObject = JSONObject()
            jsonObject.put("establishmentId", establishmentId)
            jsonObject.put("userId", userId)
            jsonObject.put("guestCount", guestCount)
            jsonObject.put("time", time)
            jsonObject.put("date", date)
            jsonObject.put("spotId", spotId)
            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val result = orderCreateDAO.postOrder(requestBody)
            Log.d("POSTORDER", "SUCCESS")
            DefaultResult.Success(result = result.result,
                exceptionMessage = result.exception?.message)
        } catch (e: Throwable) {
            Log.e("POSTORDER", "FAILURE")
            DefaultResult.Failure(e)
        }
    }

    override suspend fun getEstablishmentTime(establishmentId: Long): OrderCreateResult {
        return try {
            val result = orderCreateDAO.getEstablishmentTime(establishmentId)
            Log.d("GETESTABLISHMENTTIME", "SUCCESS")
            OrderCreateResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (exception: Exception) {
            Log.d("GETESTABLISHMENTTIME", "FAILURE")
            OrderCreateResult.Failure(exception)
        }
    }
}
