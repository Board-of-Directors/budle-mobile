package fit.budle.repository_impl

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import fit.budle.dao.EstablishmentDAO
import fit.budle.dto.establishment.*
import fit.budle.repository.EstablishmentRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.HashMap
import javax.inject.Inject

class EstablishmentRepositoryImpl @Inject constructor(
    private val establishmentDAO: EstablishmentDAO,
) : EstablishmentRepository {
    override suspend fun getEstablishment(establishmentId: Long): EstablishmentResult {
        return try {
            val result = establishmentDAO.getEstablishment(establishmentId)
            Log.d("GETESTABLISHMENT", "SUCCESS")
            EstablishmentResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.e("GETESTABLISHMENT", "FAILURE")
            EstablishmentResult.Failure(e)
        }
    }

    override suspend fun getEstablishmentAll(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentListResult {
        return try {
            val result = establishmentDAO.getEstablishmentAll(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )
            Log.d("GETESTABLISHMENTALL", "SUCCESS")
            EstablishmentListResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.e("GETESTABLISHMENTALL", "FAILURE")
            EstablishmentListResult.Failure(e)
        }
    }

    override suspend fun getCategory(): CategoriesListResult {
        return try {
            val result = establishmentDAO.getEstablishmentCategory()
            Log.d("GETCATEGORIES", "SUCCESS")
            CategoriesListResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.e("GETCATEGORIES", "FAILURE")
            CategoriesListResult.Failure(e)
        }
    }

    override suspend fun getOrder(userId: Long, status: Int?): OrderListResult {
        return try {
            val result = establishmentDAO.getOrder(userId, status = status)
            Log.d("GETORDERS", "SUCCESS")
            OrderListResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.d("GETORDERS", "FAILURE")
            OrderListResult.Failure(e)
        }
    }

    override suspend fun postOrder(
        establishmentId: Long,
        userId: Long,
        guestCount: Int,
        time: String,
        date: String,
    ): OrderResult {
        return try {
            val jsonObject = JSONObject()
            jsonObject.put("establishmentId", establishmentId)
            jsonObject.put("userId", userId)
            jsonObject.put("guestCount", guestCount)
            jsonObject.put("time", time)
            jsonObject.put("date", date)
            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val result = establishmentDAO.postOrder(requestBody)
            Log.d("OREDERREQUEST", "SUCCESS")
            OrderResult.Success(result = result.result,
                exceptionMessage = result.exception?.message)
        } catch (e: Throwable) {
            Log.e("OREDERREQUEST", "FAILURE")
            OrderResult.Failure(e)
        }
    }

    override suspend fun deleteOrder(userId: Long, orderId: Long): OrderResult {
        return try {
            val result = establishmentDAO.deleteOrder(userId, orderId)
            Log.d("DELETEORDER", "SUCCESS")
            OrderResult.Success(result = result.result,
                exceptionMessage = result.exception?.message)
        } catch (e: Throwable) {
            Log.d("GETORDER", "FAILURE")
            OrderResult.Failure(e)
        }
    }
}
