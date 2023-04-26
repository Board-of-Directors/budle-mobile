package fit.budle.model.establishment

import fit.budle.model.Owner
import fit.budle.model.tag.standard.TagResponse
import fit.budle.model.WorkingHour

data class EstablishmentResponseArray(val establishments: Array<EstablishmentResponse>, val count: Int)

data class EstablishmentResponse(
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
    val tags: Array<TagResponse>,
    val cuisineCountry: String?,
    val starsCount: Int?
)
