package fit.budle.dto.establishment

import fit.budle.dto.WorkingHour
import fit.budle.dto.customer_user.User
import fit.budle.dto.tag.standard.TagResponse

class EstablishmentDTO(
    var address: String = "",
    var category: String = "",
    var description: String? = null,
    var hasCardPayment: Boolean = false,
    var hasMap: Boolean = false,
    var id: Int = 0,
    var image: String = "",
    var map: String? = null,
    var name: String = "",
    var owner: User? = null,
    var photosInput: List<ImageDTO>? = null,
    var price: Int? = null,
    var rating: Double = 0.0,
    var tags: List<TagResponse> = emptyList(),
    var workingHours: List<WorkingHour> = emptyList(),
)
