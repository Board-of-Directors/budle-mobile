package fit.budle.model

data class EstablishmentRequest(
    val category: String?,
    val limit: Int?,
    val offset: Int?,
    val sortValue: String?,
    val name: String?,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean?,
)
