package fit.budle.repository

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import fit.budle.dto.establishment.*
import java.util.HashMap

interface EstablishmentRepository {

    suspend fun getEstablishment(establishmentId: Long): EstablishmentResult

    suspend fun getEstablishmentAll(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentListResult

    suspend fun getCategory(): CategoriesListResult

    suspend fun getOrder(userId: Long, status: Int?): OrderListResult

    suspend fun postOrder(
        establishmentId: Long,
        userId: Long,
        guestCount: Int,
        time: String,
        date: String,
    ): OrderResult

    suspend fun deleteOrder(userId: Long, orderId: Long): OrderResult
}