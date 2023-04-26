package fit.budle.repository_impl

import android.util.Log
import fit.budle.dto.Exception
import fit.budle.dao.OwnerEstablishmentDAO
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.repository.OwnerEstablishmentRepository
import javax.inject.Inject

class OwnerEstablishmentRepositoryImpl @Inject constructor(
    private val ownerEstablishmentDAO: OwnerEstablishmentDAO,
) : OwnerEstablishmentRepository {

    override suspend fun getEstablishmentArray(): EstablishmentListResult {
        return try {
            val result = ownerEstablishmentDAO.getEstablishmentArray()
            Log.d("GET_EST_ARRAY", "SUCCESS")
            EstablishmentListResult.Success(
                result = result.establishments,
                exceptionMessage = result.exception?.message
            )
        } catch (e: Throwable) {
            Log.d("GET_EST_ARRAY", "FAILURE")
            EstablishmentListResult.Failure(e)
        }
    }
}