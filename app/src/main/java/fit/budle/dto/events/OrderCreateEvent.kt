package fit.budle.dto.events

sealed interface OrderCreateEvent {
    data class postOrder(
        val establishmentId: Long,
    ) : OrderCreateEvent

    data class getEstablishmentTime(val establishmentId: Long) : OrderCreateEvent
}
