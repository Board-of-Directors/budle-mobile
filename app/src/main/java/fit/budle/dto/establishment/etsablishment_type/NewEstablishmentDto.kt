package fit.budle.dto.establishment.etsablishment_type

import fit.budle.dto.establishment.RequestWorkingHoursDto
import fit.budle.dto.establishment.establishment_field.PhotoDto
import fit.budle.dto.tag.ReturnTag

data class NewEstablishmentDto(
    var address: String = "",
    var category: String? = null,
    var description: String? = "",
    var starsCount: Int? = 5,
    var cuisineCountry: String = "Русская",
    var hasCardPayment: Boolean = false,
    var hasMap: Boolean = false,
    var image: String = "",
    var owner: Int = 11,
    var map: String? = null,
    var name: String = "",
    var photosInput: List<PhotoDto> = emptyList(),
    var price: Int = 501,
    var rating: Double = 1.2,
    var tags: List<ReturnTag> = emptyList(),
    var workingHours: List<RequestWorkingHoursDto> = emptyList(),
)
