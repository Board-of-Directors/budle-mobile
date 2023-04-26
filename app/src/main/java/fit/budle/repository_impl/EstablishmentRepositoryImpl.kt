package fit.budle.repository_impl

import android.util.Log
import fit.budle.dao.EstablishmentDAO
import fit.budle.dto.establishment.CategoriesListResult
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.dto.establishment.OrderListResult
import fit.budle.dto.establishment.OrderResult
import fit.budle.repository.EstablishmentRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class EstablishmentRepositoryImpl (
    private val establishmentDAO: EstablishmentDAO,
) : EstablishmentRepository {

    override suspend fun getEstablishment(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentListResult {
        return try {
            val result = establishmentDAO.getEstablishment(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )
            Log.d("GETESTABLISHMENT", "SUCCESS")
            EstablishmentListResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.e("GETESTABLISHMENT", "FAILURE")
            EstablishmentListResult.Failure(e)
        }
    }

    override suspend fun getCategories(): CategoriesListResult {
        return try {
            val result = establishmentDAO.getCategories()
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

    override suspend fun postOrders(
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
            val result = establishmentDAO.postOrders(requestBody)
            Log.d("OREDERREQUEST", "SUCCESS")
            OrderResult.Success(result = result.result,
                exceptionMessage = result.exception?.message)
        } catch (e: Throwable) {
            Log.e("OREDERREQUEST", "FAILURE")
            OrderResult.Failure(e)
        }
    }

    override suspend fun getOrders(userId: Long, status: Int?): OrderListResult {
        return try {
            val result = establishmentDAO.getOrders(userId, status = status)
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
