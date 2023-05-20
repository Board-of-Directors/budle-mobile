package fit.budle.repository.customer

import fit.budle.dto.result.DefaultResult
import fit.budle.dto.result.OrderListResult

interface OrderListRepository {
    suspend fun getOrder(userId: Long, status: Int?): OrderListResult

    suspend fun deleteOrder(userId: Long, orderId: Long): DefaultResult
}
