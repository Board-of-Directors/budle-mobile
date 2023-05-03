package fit.budle.repository_impl

import android.util.Log
import fit.budle.dao.EstOrderListDAO
import fit.budle.dto.result.*
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

    override suspend fun acceptOrder(
        establishmentId: Int,
        orderId: Int,
    ): AcceptEstOrderResult {
        return try {
            val response = estOrderListDAO.acceptOrder(
                establishmentId,
                orderId
            )
            Log.d("EST_ORDER_LIST_ACCEPT", "SUCCESS")
            AcceptEstOrderResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("EST_ORDER_LIST_ACCEPT", e.message!!)
            AcceptEstOrderResult.Failure(e)
        }
    }

    override suspend fun rejectOrder(
        establishmentId: Int,
        orderId: Int,
    ): RejectEstOrderResult {
        return try {
            val response = estOrderListDAO.rejectOrder(
                establishmentId,
                orderId
            )
            Log.d("EST_ORDER_LIST_REJECT", "SUCCESS")
            RejectEstOrderResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("EST_ORDER_LIST_REJECT", e.message!!)
            RejectEstOrderResult.Failure(e)
        }
    }
}