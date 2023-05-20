package fit.budle.dto.result

import fit.budle.dto.order.ScheduleDay

sealed class OrderCreateResult {
    data class Success(val result: Array<ScheduleDay>, val exceptionMessage: String?) :
        OrderCreateResult()
    data class Failure(val throwable: Throwable) : OrderCreateResult()
}
