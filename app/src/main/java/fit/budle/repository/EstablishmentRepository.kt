package fit.budle.repository

import fit.budle.dto.establishment.CategoriesListResult
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.dto.establishment.OrderListResult
import fit.budle.dto.establishment.OrderResult

interface EstablishmentRepository {

    suspend fun getEstablishment(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentListResult

    suspend fun getCategories(): CategoriesListResult

    suspend fun getOrders(userId: Long, status: Int?): OrderListResult

    suspend fun postOrders(
        establishmentId: Long,
        userId: Long,
        guestCount: Int,
        time: String,
        date: String,
    ): OrderResult

    suspend fun deleteOrder(userId: Long, orderId: Long): OrderResult
}