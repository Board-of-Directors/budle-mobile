package fit.budle.event.customer

sealed interface OrderCreateEvent {
    data class PostOrder(val establishmentId: Long) : OrderCreateEvent
    data class GetEstablishmentTime(val establishmentId: Long) : OrderCreateEvent
    object SetSeatAmount : OrderCreateEvent
    object SetDay : OrderCreateEvent
    object SetTime : OrderCreateEvent
}
