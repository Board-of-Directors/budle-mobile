package fit.budle.dto.result

import fit.budle.dto.order.Booking

sealed class OrderListResult {
    data class Success(val result: Array<Booking>, val exceptionMessage: String?) :
        OrderListResult()
    data class Failure(val throwable: Throwable) : OrderListResult()
}
