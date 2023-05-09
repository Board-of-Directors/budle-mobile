package fit.budle.dto.establishment

import fit.budle.dto.establishment.etsablishment_type.EstablishmentBasicDto
import fit.budle.dto.establishment.etsablishment_type.EstablishmentExtendedDto
import fit.budle.dto.order.Booking

sealed class EstablishmentListResult {
    data class Success(val result: EstablishmentDtoArray, val exceptionMessage: String?) :
        EstablishmentListResult()
    data class Failure(val throwable: Throwable) : EstablishmentListResult()
}

sealed class EstablishmentResult {
    data class Success(val result: EstablishmentDto, val exceptionMessage: String?) :
        EstablishmentResult()
    data class Failure(val throwable: Throwable) : EstablishmentResult()
}

sealed class CategoriesListResult {
    data class Success(val result: Array<String>, val exceptionMessage: String?) :
        CategoriesListResult()
    data class Failure(val throwable: Throwable) : CategoriesListResult()
}

sealed class OrderResult {
    data class Success(val result: Boolean?, val exceptionMessage: String?) : OrderResult()
    data class Failure(val throwable: Throwable) : OrderResult()
}

sealed class OrderListResult {
    data class Success(val result: Array<Booking>, val exceptionMessage: String?) :
        OrderListResult()
    data class Failure(val throwable: Throwable) : OrderListResult()
}
