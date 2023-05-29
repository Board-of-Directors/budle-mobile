package fit.budle.repository.customer

import fit.budle.dto.order.RequestOrderDto
import fit.budle.request.result.DefaultResult
import fit.budle.request.result.customer.OrderCreateResult

interface OrderCreateRepository {
    suspend fun postOrder(requestOrderDto: RequestOrderDto): DefaultResult

    suspend fun getEstablishmentTime(establishmentId: Long): OrderCreateResult

}
