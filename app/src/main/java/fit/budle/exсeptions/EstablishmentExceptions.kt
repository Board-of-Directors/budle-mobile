package fit.budle.exсeptions

sealed class EstablishmentExceptions(message: String) : Exception(message) {
    object EstablishmentsNotFoundException : EstablishmentExceptions("Establishment list not found")
    object CategoriesNotFoundException : EstablishmentExceptions("Category list not found")
}