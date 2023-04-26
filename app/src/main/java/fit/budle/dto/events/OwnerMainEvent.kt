package fit.budle.dto.events

sealed class OwnerMainEvent {
    object GetEstablishmentsEvent : OwnerMainEvent()
}
