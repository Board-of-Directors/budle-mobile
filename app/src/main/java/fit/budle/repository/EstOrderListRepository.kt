package fit.budle.repository

import fit.budle.dto.result.DeleteEstOrderResult
import fit.budle.dto.result.GetEstOrderListResult
import fit.budle.dto.result.PutEstOrderResult

interface EstOrderListRepository {
    suspend fun getOrderList(establishmentId: Int, status: Int = 0): GetEstOrderListResult
    suspend fun putOrder(establishmentId: Int, orderId: Int, status: Int): PutEstOrderResult
    suspend fun deleteOrder(establishmentId: Int, orderId: Int): DeleteEstOrderResult
}