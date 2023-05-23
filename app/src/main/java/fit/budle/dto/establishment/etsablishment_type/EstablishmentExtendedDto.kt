package fit.budle.dto.establishment.etsablishment_type

import fit.budle.dto.WorkingHour
import fit.budle.dto.establishment.establishment_field.PhotoDto
import fit.budle.dto.tag.standard.TagResponse

data class EstablishmentExtendedDto(
    val id: Int = 0,
    var name: String = "",
    var category: String = "",
    var address: String = "",
    var image: String = "",
    val rating: Double = 1.2,
    var description: String = "",
    var photos: List<PhotoDto> = emptyList(),
    var tags: List<TagResponse> = emptyList(),
    var workingHours: List<WorkingHour> = emptyList()
)
