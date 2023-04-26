package fit.budle.dto.establishment

import fit.budle.dto.order.Booking

sealed class OrderResult {
    data class Success(val result: Boolean?, val exceptionMessage: String?) : OrderResult()
    data class Failure(val throwable: Throwable) : OrderResult()
}

sealed class EstablishmentListResult {
    data class Success(val result: EstablishmentResponseArray, val exceptionMessage: String?) :
        EstablishmentListResult()
    data class Failure(val throwable: Throwable) : EstablishmentListResult()
}

sealed class CategoriesListResult {
    data class Success(val result: Array<String>, val exceptionMessage: String?) :
        CategoriesListResult()
    data class Failure(val throwable: Throwable) : CategoriesListResult()
}

sealed class OrderListResult {
    data class Success(val result: Array<Booking>, val exceptionMessage: String?) :
        OrderListResult()
    data class Failure(val throwable: Throwable) : OrderListResult()
}