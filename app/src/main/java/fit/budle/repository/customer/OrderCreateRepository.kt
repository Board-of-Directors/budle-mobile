package fit.budle.repository.customer

import fit.budle.dto.result.DefaultResult
import fit.budle.dto.result.OrderCreateResult

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
