package fit.budle.event.business

sealed interface OwnerMainEvent {
    object GetEstListEvent : OwnerMainEvent
}