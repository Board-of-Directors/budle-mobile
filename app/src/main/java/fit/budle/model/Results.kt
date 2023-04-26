package fit.budle.model

import fit.budle.model.establishment.EstablishmentResponseArray

sealed class OrderResult {
    object LOADING : OrderResult()
    data class Success(val result: Boolean?, val exceptionMessage: String?) : OrderResult()
    data class Failure(val throwable: Throwable) : OrderResult()
}

sealed class EstablishmentListResult {
    object LOADING : EstablishmentListResult()
    data class Success(val result: EstablishmentResponseArray, val exceptionMessage: String?) : EstablishmentListResult()
    data class Failure(val throwable: Throwable) : EstablishmentListResult()
}

sealed class CategoriesListResult {
    object LOADING : CategoriesListResult()
    data class Success(val result: Array<String>, val exceptionMessage: String?) : CategoriesListResult()
    data class Failure(val throwable: Throwable) : CategoriesListResult()
}

sealed class OrderListResult {
    object LOADING : OrderListResult()
    data class Success(val result: Array<Booking>, val exceptionMessage: String?) : OrderListResult()
    data class Failure(val throwable: Throwable) : OrderListResult()
}