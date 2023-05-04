package fit.budle.repository_impl

import android.util.Log
import fit.budle.dao.EstOrderListDAO
import fit.budle.dto.result.GetEstOrderListResult
import fit.budle.dto.result.PutEstOrderStatusResult
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

    override suspend fun putOrderStatus(
        establishmentId: Int,
        orderId: Int,
        status: Int,
    ): PutEstOrderStatusResult {
        return try {
            val response = estOrderListDAO.putOrderStatus(
                establishmentId,
                orderId,
                status
            )
            Log.d("PUT_ORDER_STATUS_ACCEPT", "SUCCESS")
            PutEstOrderStatusResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("PUT_ORDER_STATUS_ACCEPT", e.message!!)
            PutEstOrderStatusResult.Failure(e)
        }
    }
}