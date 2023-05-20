package fit.budle.di.establishment

data class EstablishmentRequest(
    val category: String? = null,
    val limit: Int? = null,
    val offset: Int? = null,
    val sortValue: String? = null,
    val name: String? = null,
    val hasCardPayment: Boolean? = null,
    val hasMap: Boolean? = null,
)
