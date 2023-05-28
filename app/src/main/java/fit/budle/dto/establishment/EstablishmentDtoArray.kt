package fit.budle.dto.establishment

import fit.budle.dto.WorkingHour
import fit.budle.dto.customer_user.User
import fit.budle.dto.tag.standard.TagResponse

data class EstablishmentDtoArray(val establishments: Array<EstablishmentDto>, val count: Int)

data class EstablishmentDto(
    val id: Long,
    val name: String,
    val description: String?,
    val address: String,
    val owner: User,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean,
    val map: String?,
    val category: String,
    val image: String?,
    val rating: Double?,
    val price: Int?,
    val workingHours: Array<WorkingHour>,
    val tags: Array<TagResponse>,
    val photos: Array<String>? = emptyArray(),
    val cuisineCountry: String?,
    val starsCount: Int?,
)
