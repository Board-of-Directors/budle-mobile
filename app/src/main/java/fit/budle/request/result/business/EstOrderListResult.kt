package fit.budle.dto.result

import fit.budle.dto.Exception
import fit.budle.dto.order.BusinessOrderDto

sealed interface GetEstOrderListResult {
    data class Success(val result: List<BusinessOrderDto>, val exception: Exception?) : GetEstOrderListResult
    data class Failure(val throwable: Throwable) : GetEstOrderListResult
}

sealed interface PutEstOrderStatusResult {
    data class Success(val result: BusinessOrderDto?, val exception: Exception?) :
        PutEstOrderStatusResult

    data class Failure(val throwable: Throwable) : PutEstOrderStatusResult
}