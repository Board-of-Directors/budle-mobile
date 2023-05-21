package fit.budle.event.customer

sealed interface OrderCreateEvent {
    data class postOrder(
        val establishmentId: Long,
    ) : OrderCreateEvent

    data class getEstablishmentTime(val establishmentId: Long) : OrderCreateEvent
}
