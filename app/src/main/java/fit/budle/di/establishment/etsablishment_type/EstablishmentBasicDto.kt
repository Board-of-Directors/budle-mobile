package fit.budle.di.establishment.etsablishment_type

data class EstablishmentBasicDto(
    val id: Int,
    val name: String,
    val category: String,
    val address: String,
    val image: String,
    val rating: Double
)