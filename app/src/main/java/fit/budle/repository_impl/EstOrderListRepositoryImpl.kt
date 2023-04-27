package fit.budle.repository_impl

import android.util.Log
import fit.budle.dao.EstOrderListDAO
import fit.budle.dto.result.DeleteEstOrderResult
import fit.budle.dto.result.GetEstOrderListResult
import fit.budle.dto.result.PutEstOrderResult
import fit.budle.repository.EstOrderListRepository
import javax.inject.Inject

class EstOrderListRepositoryImpl @Inject constructor(
    private val estOrderListDAO: EstOrderListDAO,
) : EstOrderListRepository {

    override suspend fun getOrderList(
        establishmentId: Int,
        status: Int,
    ): GetEstOrderListResult {
        return try {
            val response = estOrderListDAO.getOrderList(establishmentId, status)
            Log.d("EST_ORDER_LIST_GET", "SUCCESS")
            GetEstOrderListResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("EST_ORDER_LIST_GET", e.message!!)
            GetEstOrderListResult.Failure(e)
        }
    }

    override suspend fun putOrder(
        establishmentId: Int,
        orderId: Int,
        status: Int,
    ): PutEstOrderResult {
        return try {
            val response = estOrderListDAO.putOrder(establishmentId, orderId, status)
            Log.d("PUT_EST_ORDER", "SUCCESS")
            PutEstOrderResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("PUT_EST_ORDER", e.message!!)
            PutEstOrderResult.Failure(e)
        }
    }

    override suspend fun deleteOrder(
        establishmentId: Int,
        orderId: Int,
    ): DeleteEstOrderResult {
        return try {
            val response = estOrderListDAO.deleteOrder(establishmentId,orderId)
            Log.d("DELETE_EST_ORDER","SUCCESS")
            DeleteEstOrderResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("DELETE_EST_ORDER", e.message!!)
            DeleteEstOrderResult.Failure(e)
        }
    }
}