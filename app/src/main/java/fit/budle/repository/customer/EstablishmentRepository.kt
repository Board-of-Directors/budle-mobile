package fit.budle.repository.customer

import fit.budle.dto.establishment.CategoriesListResult
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.dto.establishment.EstablishmentResult

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
