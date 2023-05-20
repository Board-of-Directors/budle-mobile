package fit.budle.dto.order

import fit.budle.di.establishment.Establishment
import fit.budle.di.establishment.EstablishmentDto

data class Booking(
    val id: Long,
    val userId: Long,
    val status: Int,
    val establishment: EstablishmentDto,
    @Transient
    var establishmentImage: Establishment,
    val guestCount: Int,
    var date: String,
    var startTime: String,
    var endTime: String
)

enum class BookingStatus(val value: Int, val message: String) {
    WAIT(0, "Бронь в ожидании"),
    CONFIRM(1, "Бронь подтверждена"),
    REJECT(2, "Бронь отклонена");

    companion object {
        fun create(x: Int): BookingStatus {
            return when (x) {
                0 -> WAIT
                1 -> CONFIRM
                2 -> REJECT
                else -> throw IllegalStateException()
            }
        }
    }
}
