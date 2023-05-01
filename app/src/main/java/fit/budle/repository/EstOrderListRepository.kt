package fit.budle.repository

import fit.budle.dto.response.EstOrderResponse
import fit.budle.dto.result.*

interface EstOrderListRepository {
    suspend fun getOrderList(establishmentId: Int, status: Int = 0): GetEstOrderListResult
    suspend fun acceptOrder(establishmentId: Int, orderId: Int): AcceptEstOrderResult
    suspend fun rejectOrder(establishmentId: Int, orderId: Int): RejectEstOrderResult
}