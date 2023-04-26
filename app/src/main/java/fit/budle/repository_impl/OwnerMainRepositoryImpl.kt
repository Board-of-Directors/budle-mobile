package fit.budle.repository_impl

import android.util.Log
import fit.budle.dao.OwnerMainDAO
import fit.budle.dto.result.OwnerEstResult
import fit.budle.repository.OwnerMainRepository
import javax.inject.Inject

class OwnerMainRepositoryImpl @Inject constructor(
    private val ownerMainDAO: OwnerMainDAO,
) : OwnerMainRepository {

    override suspend fun getEstablishmentList(): OwnerEstResult {
        return try {
            val response = ownerMainDAO.getEstablishmentList()
            Log.d("GET_EST_LIST", "SUCCESS")
            OwnerEstResult.Success(
                result = response.result.establishments,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("GET_EST_LIST", e.message!!)
            OwnerEstResult.Failure(e)
        }
    }
}