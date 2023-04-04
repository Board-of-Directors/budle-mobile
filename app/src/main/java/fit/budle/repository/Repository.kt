package fit.budle.repository

import fit.budle.model.*
import okhttp3.RequestBody
import retrofit2.http.*

interface Repository {

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