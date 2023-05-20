package fit.budle.repository_impl.business

import android.util.Log
import fit.budle.dao.business.OwnerMainDAO
import fit.budle.dto.result.OwnerEstResult
import fit.budle.repository.business.OwnerMainRepository
import javax.inject.Inject

class OwnerMainRepositoryImpl @Inject constructor(
    private val ownerMainDAO: OwnerMainDAO,
) : OwnerMainRepository {

    override suspend fun getEstablishmentList(id: Int): OwnerEstResult {
        return try {
            val response = ownerMainDAO.getEstablishmentList(id)
            val headers = response.headers()
            Log.d("GET_EST_LIST", "SUCCESS")
            OwnerEstResult.Success(
                result = response.body()!!.result,
                exception = null
            )
        } catch (e: Throwable) {
            Log.d("GET_EST_LIST", e.message!!)
            OwnerEstResult.Failure(e)
        }
    }
}