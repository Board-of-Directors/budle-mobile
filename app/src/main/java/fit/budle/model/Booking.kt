package fit.budle.model

import java.time.LocalDate
import java.time.LocalTime

data class Booking(
    val userId: Long,
    val status: Int,
    val establishment: Establishment,
    @Transient
    var establishmentImage: EstablishmentWithImage,
    val guestCount: Int,
    val date: String,
    val time: String

)
