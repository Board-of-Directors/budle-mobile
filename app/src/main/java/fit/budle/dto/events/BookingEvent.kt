package fit.budle.dto.events

sealed interface BookingEvent {
    data class GetBookings(val establishmentId: Int, val status: Int) : BookingEvent
    data class AcceptBooking(val establishmentId: Int, val orderId: Int) : BookingEvent
    data class RejectBooking(val establishmentId: Int, val orderId: Int) : BookingEvent
}