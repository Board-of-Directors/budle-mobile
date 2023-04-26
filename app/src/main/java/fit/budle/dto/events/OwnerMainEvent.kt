package fit.budle.dto.events

sealed interface OwnerMainEvent {
    object GetEstListEvent : OwnerMainEvent
}