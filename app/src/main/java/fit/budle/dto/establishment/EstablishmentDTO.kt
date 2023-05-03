package fit.budle.dto.establishment

import fit.budle.dto.WorkingHour
import fit.budle.dto.establishment.establishment_field.PhotoDto
import fit.budle.dto.tag.standard.TagResponse

data class ReturnTag(val name: String)

class EstablishmentDTO(
    var address: String = "",
    var category: String? = null,
    var description: String? = "",
    var starsCount: Int? = null,
    var cuisineCountry: String? = null,
    var hasCardPayment: Boolean = false,
    var hasMap: Boolean = false,
    var image: String = "",
    var owner: Int = 11,
    var map: String = "",
    var name: String = "",
    var photosInput: List<PhotoDto> = listOf(),
    var price: Int = 501,
    var rating: Double = 1.2,
    var tags: List<ReturnTag> = emptyList(),
    var workingHours: List<WorkingHour> = emptyList(),
)
