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
}
