package fit.budle.event.business

sealed interface OwnerMainEvent {
    object GetEstListEvent : OwnerMainEvent
    object PutEstablishment : OwnerMainEvent
    object GetEstablishment : OwnerMainEvent
    object DeleteEstablishment : OwnerMainEvent
}