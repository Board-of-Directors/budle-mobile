package fit.budle.repository.customer

import fit.budle.di.establishment.CategoriesListResult
import fit.budle.di.establishment.EstablishmentListResult
import fit.budle.di.establishment.EstablishmentResult

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
