package fit.budle.model

data class Establishment(
    val id: Long,
    val name: String,
    val description: String?,
    val address: String,
    val owner: Owner,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean?,
    val map: String?,
    val category: String,
    val image: String?,
    val rating: Double?,
    val price: Int?,
    val workingHours: Array<WorkingHour>,
    val tags: Array<TagParse>,
    val cuisineCountry: String?,
    val starsCount: Int?
)
