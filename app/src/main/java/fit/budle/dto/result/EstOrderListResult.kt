package fit.budle.dto.result

import fit.budle.dto.Exception
import fit.budle.dto.order.Booking

sealed interface GetEstOrderListResult {
    data class Success(val result: List<Booking>, val exception: Exception?) : GetEstOrderListResult
    data class Failure(val throwable: Throwable) : GetEstOrderListResult
}

sealed interface PutEstOrderResult {
    data class Success(val result: Booking?, val exception: Exception?) : PutEstOrderResult
    data class Failure(val throwable: Throwable) : PutEstOrderResult
}

sealed interface DeleteEstOrderResult {
    data class Success(val result: Booking?, val exception: Exception?) : DeleteEstOrderResult
    data class Failure(val throwable: Throwable) : DeleteEstOrderResult
}
