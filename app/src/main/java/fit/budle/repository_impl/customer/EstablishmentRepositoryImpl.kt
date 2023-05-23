package fit.budle.repository_impl.customer

import android.util.Log
import fit.budle.dao.customer.EstablishmentDAO
import fit.budle.dto.establishment.CategoriesListResult
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.dto.establishment.EstablishmentResult
import fit.budle.repository.customer.EstablishmentRepository
import javax.inject.Inject

class EstablishmentRepositoryImpl @Inject constructor(
    private val establishmentDAO: EstablishmentDAO,
) : EstablishmentRepository {
    override suspend fun getEstablishment(establishmentId: Long): EstablishmentResult {
        return try {
            val result = establishmentDAO.getEstablishment(establishmentId)
            Log.d("GETESTABLISHMENT", "SUCCESS")
            EstablishmentResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.e("GETESTABLISHMENT", "FAILURE")
            EstablishmentResult.Failure(e)
        }
    }

    override suspend fun getEstablishmentAll(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentListResult {
        return try {
            val result = establishmentDAO.getEstablishmentAll(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )
            Log.d("GETESTABLISHMENTALL", "SUCCESS")
            EstablishmentListResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.e("GETESTABLISHMENTALL", "FAILURE")
            EstablishmentListResult.Failure(e)
        }
    }

    override suspend fun getCategory(): CategoriesListResult {
        return try {
            val result = establishmentDAO.getEstablishmentCategory()
            Log.d("GETCATEGORIES", "SUCCESS")
            CategoriesListResult.Success(
                result = result.result,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.e("GETCATEGORIES", "FAILURE")
            CategoriesListResult.Failure(e)
        }
    }
}
