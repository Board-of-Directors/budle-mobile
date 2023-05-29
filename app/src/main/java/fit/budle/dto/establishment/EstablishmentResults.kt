package fit.budle.dto.establishment

sealed class EstablishmentListResult {
    data class Success(val result: EstablishmentDtoArray, val exception: String?) :
        EstablishmentListResult()

    data class Failure(val exception: String?) : EstablishmentListResult()
}

sealed class EstablishmentResult {
    data class Success(val result: EstablishmentDto, val exception: String?) :
        EstablishmentResult()

    data class Failure(val exception: String?) : EstablishmentResult()
}

sealed class CategoriesListResult {
    data class Success(val result: Array<String>, val exception: String?) :
        CategoriesListResult()

    data class Failure(val exception: String?) : CategoriesListResult()
}
