package fit.budle.repository_impl.customer

import android.util.Log
import fit.budle.dao.customer.BusinessLoginDAO
import fit.budle.dto.customer_user.RequestUser
import fit.budle.repository.customer.BusinessLoginRepository
import fit.budle.request.result.customer.BusinessLoginResult
import javax.inject.Inject

class BusinessLoginRepositoryImpl @Inject constructor(
    private val businessLoginDAO: BusinessLoginDAO,
) : BusinessLoginRepository {
    override suspend fun postBusinessLogin(requestUser: RequestUser): BusinessLoginResult {
        return try {
            val response = businessLoginDAO.postBusinessLogin(requestUser)
            Log.d("POST_BUS_LOGIN", "SUCCESS")
            BusinessLoginResult.Success(
                response.body()!!.result,
                response.headers(),
                response.body()!!.exception
            )
        } catch (e: Throwable) {
            Log.d("POST_BUS_LOGIN", e.message!!)
            BusinessLoginResult.Failure(e)
        }
    }
}