package fit.budle.repository_impl.customer

import android.util.Log
import fit.budle.dao.customer.OrderCreateDAO
import fit.budle.dto.ResponseException
import fit.budle.dto.order.RequestOrderDto
import fit.budle.repository.customer.OrderCreateRepository
import fit.budle.request.result.DefaultResult
import fit.budle.request.result.customer.OrderCreateResult
import javax.inject.Inject

class OrderCreateRepositoryImpl @Inject constructor(
    private val orderCreateDAO: OrderCreateDAO,
) : OrderCreateRepository {
    override suspend fun postOrder(requestOrderDto: RequestOrderDto): DefaultResult {

        val response = orderCreateDAO.postOrder(requestOrderDto)

        return if (response.body() == null) {

            Log.e("POST_ORDER1", ResponseException.NULL_BODY)
            DefaultResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.d("POST_ORDER", "SUCCESS")
            DefaultResult.Success(
                result = response.body()!!.result,
                exception = null
            )

        } else {
            Log.e("POST_ORDER2", response.body()!!.exception!!.message)
            DefaultResult.Failure(response.body()!!.exception!!.message)
        }
    }

    override suspend fun getEstablishmentTime(establishmentId: Long): OrderCreateResult {

        val response = orderCreateDAO.getEstablishmentTime(establishmentId)

        return if (response.body() == null) {

            Log.e("GET_EST_TIME", ResponseException.NULL_BODY)
            OrderCreateResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.d("GET_EST_TIME", "SUCCESS")
            OrderCreateResult.Success(
                result = response.body()!!.result,
                exception = null
            )

        } else {
            Log.e("GET_EST_TIME", response.body()!!.exception!!.message)
            OrderCreateResult.Failure(response.body()!!.exception!!.message)
        }
    }
}
