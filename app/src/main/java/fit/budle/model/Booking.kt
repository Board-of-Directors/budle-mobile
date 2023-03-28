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
