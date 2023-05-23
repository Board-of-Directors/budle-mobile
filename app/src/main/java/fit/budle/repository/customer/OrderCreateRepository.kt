package fit.budle.repository.customer

import fit.budle.request.result.DefaultResult
import fit.budle.request.result.customer.OrderCreateResult

interface OrderCreateRepository {

    suspend fun postOrder(
        establishmentId: Long,
        userId: Long,
        guestCount: Int,
        time: String,
        date: String,
        spotId: Int?
    ): DefaultResult

    suspend fun getEstablishmentTime(
        establishmentId: Long
    ): OrderCreateResult

}
