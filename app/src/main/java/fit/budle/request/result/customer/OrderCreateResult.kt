package fit.budle.request.result.customer

import fit.budle.dto.Exception
import fit.budle.dto.order.ScheduleDay

sealed interface OrderCreateResult {
    data class Success(val result: Array<ScheduleDay>, val exceptionMessage: String?) :
        OrderCreateResult

    data class Failure(val throwable: Throwable) : OrderCreateResult
}

sealed interface GetEstablishmentMapResult {
    data class Success(val result: String?, val exception: Exception?) :
        GetEstablishmentMapResult

    data class Failure(val throwable: Throwable) : GetEstablishmentMapResult
}