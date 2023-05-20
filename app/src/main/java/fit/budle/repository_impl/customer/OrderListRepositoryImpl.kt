package fit.budle.repository_impl.customer

import android.util.Log
import fit.budle.dao.customer.OrderListDAO
import fit.budle.dto.result.DefaultResult
import fit.budle.dto.result.OrderListResult
import fit.budle.repository.customer.OrderListRepository
import javax.inject.Inject

class OrderListRepositoryImpl @Inject constructor(
    private val orderDAO: OrderListDAO,
) : OrderListRepository {
    override suspend fun getOrder(userId: Long, status: Int?): OrderListResult {
        return try {
            val result = orderDAO.getOrder(userId, status = status)
            Log.d("GETORDER", "SUCCESS")
            OrderListResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.d("GETORDER", "FAILURE")
            OrderListResult.Failure(e)
        }
    }

    override suspend fun deleteOrder(userId: Long, orderId: Long): DefaultResult {
        return try {
            val result = orderDAO.deleteOrder(userId, orderId)
            Log.d("DELETEORDER", "SUCCESS")
            DefaultResult.Success(result = result.result,
                exceptionMessage = result.exception?.message)
        } catch (e: Throwable) {
            Log.d("DELETEORDER", "FAILURE")
            DefaultResult.Failure(e)
        }
    }
}
