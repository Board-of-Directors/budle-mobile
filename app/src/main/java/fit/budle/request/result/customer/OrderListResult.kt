package fit.budle.request.result.customer

import fit.budle.dto.order.Booking

sealed interface OrderListResult {
    data class Success(val result: Array<Booking>, val exceptionMessage: String?) :
        OrderListResult

    data class Failure(val throwable: Throwable) : OrderListResult
}
