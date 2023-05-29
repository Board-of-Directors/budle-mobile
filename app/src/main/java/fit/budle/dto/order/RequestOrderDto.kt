package fit.budle.dto.order

data class RequestOrderDto(
    var date: String = "",
    var establishmentId: Int = 0,
    var guestCount: Int = 0,
    var spotId: Int? = null,
    var time: String = "",
    var userId: Int? = null,
)
