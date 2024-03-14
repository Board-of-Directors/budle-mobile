package fit.budle.dto.order

data class Booking(
    val id: Long,
    val guestCount: Int,
    var date: String,
    var startTime: String,
    var endTime: String,
    val status: Int,
    val establishmentId: Int,
    val guestName: String,
    val image: String,
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
