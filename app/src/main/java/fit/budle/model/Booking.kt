package fit.budle.model

data class Booking(
    val id: Long,
    val userId: Long,
    val status: Int,
    val establishment: Establishment,
    @Transient
    var establishmentImage: EstablishmentWithImage,
    val guestCount: Int,
    var date: String,
    var time: String

)
