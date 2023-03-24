package fit.budle.model

data class Establishment(
    val id: Int,
    val name: String,
    val description: String?,
    val address: String,
    val owner: Owner,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean?,
    val category: String,
    val image: String?,
    val rating: Double?,
    val price: Int?
)
