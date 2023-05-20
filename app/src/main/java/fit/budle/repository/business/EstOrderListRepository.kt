package fit.budle.repository.business

import fit.budle.dto.result.GetEstOrderListResult
import fit.budle.dto.result.PutEstOrderStatusResult

interface EstOrderListRepository {
    suspend fun getOrderList(establishmentId: Int, status: Int = 0): GetEstOrderListResult
    suspend fun putOrderStatus(
        establishmentId: Int,
        orderId: Int,
        status: Int,
    ): PutEstOrderStatusResult
}