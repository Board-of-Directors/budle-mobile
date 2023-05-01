package fit.budle.dto.result

import fit.budle.dto.Exception
import fit.budle.dto.order.Booking
import fit.budle.dto.order.BusinessOrderDto

sealed interface GetEstOrderListResult {
    data class Success(val result: List<BusinessOrderDto>, val exception: Exception?) : GetEstOrderListResult
    data class Failure(val throwable: Throwable) : GetEstOrderListResult
}

sealed interface AcceptEstOrderResult {
    data class Success(val result: BusinessOrderDto?, val exception: Exception?) : AcceptEstOrderResult
    data class Failure(val throwable: Throwable) : AcceptEstOrderResult
}

sealed interface RejectEstOrderResult {
    data class Success(val result: BusinessOrderDto?, val exception: Exception?) : RejectEstOrderResult
    data class Failure(val throwable: Throwable) : RejectEstOrderResult
}
