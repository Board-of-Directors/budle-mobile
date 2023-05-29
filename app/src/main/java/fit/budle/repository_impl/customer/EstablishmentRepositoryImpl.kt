package fit.budle.repository_impl.customer

import android.util.Log
import fit.budle.dao.customer.EstablishmentDAO
import fit.budle.dto.ResponseException
import fit.budle.dto.establishment.CategoriesListResult
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.dto.establishment.EstablishmentResult
import fit.budle.repository.customer.EstablishmentRepository
import javax.inject.Inject

class EstablishmentRepositoryImpl @Inject constructor(
    private val establishmentDAO: EstablishmentDAO,
) : EstablishmentRepository {
    override suspend fun getEstablishment(establishmentId: Long): EstablishmentResult {

        val response = establishmentDAO.getEstablishment(establishmentId)

        return if (response.body() == null) {

            Log.e("GET_ESTABLISHMENT", ResponseException.NULL_BODY)
            EstablishmentResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.d("GET_ESTABLISHMENT", "SUCCESS")
            EstablishmentResult.Success(
                result = response.body()!!.result,
                exception = null
            )

        } else {
            Log.e("GET_ESTABLISHMENT", response.body()!!.exception!!.message)
            EstablishmentResult.Failure(response.body()!!.exception!!.message)
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

        val response = establishmentDAO.getEstablishmentAll(
            category, limit, offset, sortValue, name, hasCardPayment, hasMap
        )

        return if (response.body() == null) {

            Log.e("GET_EST_ALL", ResponseException.NULL_BODY)
            EstablishmentListResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.i("GET_EST_ALL", "SUCCESS")
            EstablishmentListResult.Success(
                result = response.body()!!.result,
                exception = null
            )

        } else {
            Log.e("GET_EST_ALL", response.body()!!.exception!!.message)
            EstablishmentListResult.Failure(response.body()!!.exception!!.message)
        }
    }

    override suspend fun getCategory(): CategoriesListResult {

        val response = establishmentDAO.getEstablishmentCategory()

        return if (response.body() == null) {

            Log.e("GET_CATEGORY", ResponseException.NULL_BODY)
            CategoriesListResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.d("GET_CATEGORY", "SUCCESS")
            CategoriesListResult.Success(
                result = response.body()!!.result,
                exception = null
            )

        } else {
            Log.e("GET_CATEGORY", response.body()!!.exception!!.message)
            CategoriesListResult.Failure(response.body()!!.exception!!.message)
        }
    }
}
