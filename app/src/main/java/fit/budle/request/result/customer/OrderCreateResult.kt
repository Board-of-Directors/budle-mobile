package fit.budle.request.result.customer

import fit.budle.dto.Exception
import fit.budle.dto.order.ScheduleDay

sealed interface OrderCreateResult {
    data class Success(val result: List<ScheduleDay>, val exception: String?) : OrderCreateResult
    data class Failure(val exception: String?) : OrderCreateResult
}

sealed interface GetEstablishmentMapResult {
    data class Success(val result: String?, val exception: Exception?) :
        GetEstablishmentMapResult

    data class Failure(val throwable: Throwable) : GetEstablishmentMapResult
}