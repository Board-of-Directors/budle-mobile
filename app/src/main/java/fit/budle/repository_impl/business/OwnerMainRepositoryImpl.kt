package fit.budle.repository_impl.business

import android.util.Log
import fit.budle.dao.business.OwnerMainDAO
import fit.budle.dto.ResponseException
import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.result.DeleteEstablishmentResult
import fit.budle.dto.result.PutEstablishmentResult
import fit.budle.repository.business.OwnerMainRepository
import fit.budle.request.result.business.OwnerEstResult
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

    override suspend fun putEstablishment(
        establishmentDto: NewEstablishmentDto,
        establishmentId: Int,
    ): PutEstablishmentResult {

        val response = ownerMainDAO.putEstablishment(
            establishmentDto, establishmentId
        )

        return if (response.body() == null) {

            Log.w("PUT_EST", ResponseException.NULL_BODY)
            PutEstablishmentResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.i("PUT_EST", "SUCCESS")
            PutEstablishmentResult.Success(
                result = response.body()!!.result,
                exception = null
            )

        } else {
            Log.w("PUT_EST", response.body()!!.exception!!.message)
            PutEstablishmentResult.Failure(response.body()!!.exception!!.message)
        }
    }

    override suspend fun deleteEstablishment(establishmentId: Int): DeleteEstablishmentResult {

        val response = ownerMainDAO.deleteEstablishment(establishmentId)

        return if (response.body() == null) {

            Log.w("DELETE_EST", ResponseException.NULL_BODY)
            DeleteEstablishmentResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.i("DELETE_EST", "SUCCESS")
            DeleteEstablishmentResult.Success(
                result = response.body()!!.result,
                exception = null
            )

        } else {
            Log.w("DELETE_EST", response.body()!!.exception!!.message)
            DeleteEstablishmentResult.Failure(response.body()!!.exception!!.message)
        }
    }
}