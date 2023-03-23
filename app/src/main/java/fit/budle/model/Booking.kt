package fit.budle.model

import java.time.LocalDate
import java.time.LocalTime

data class Booking(
    val userId: Long,
    val establishmentId: Long,
    val guestCount: Int,
    val date: LocalDate,
    val time: LocalTime

)
