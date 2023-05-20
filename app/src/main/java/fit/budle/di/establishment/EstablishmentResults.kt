package fit.budle.di.establishment

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
