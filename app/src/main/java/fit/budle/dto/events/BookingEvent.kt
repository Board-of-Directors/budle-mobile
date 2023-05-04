package fit.budle.dto.events

sealed interface BookingEvent {
    data class GetBookings(val establishmentId: Int, val status: Int) : BookingEvent
    data class PutBookingStatus(val establishmentId: Int, val orderId: Int, val status: Int) :
        BookingEvent
}