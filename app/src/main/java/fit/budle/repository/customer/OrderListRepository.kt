package fit.budle.repository.customer

import fit.budle.request.result.DefaultResult
import fit.budle.request.result.customer.OrderListResult

interface OrderListRepository {
    suspend fun getOrder(userId: Long, status: Int?): OrderListResult

    suspend fun deleteOrder(userId: Long, orderId: Long): DefaultResult
}
