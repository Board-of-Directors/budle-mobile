package fit.budle.dto.events

sealed interface BookingEvent {
    data class GetBookings(val establishmentId: Int, val status: Int = 0) : BookingEvent
    data class PutBooking(val establishmentId: Int, val orderId: Int, val status: Int) : BookingEvent
    data class DeleteBooking(val establishmentId: Int, val orderId: Int) : BookingEvent
}