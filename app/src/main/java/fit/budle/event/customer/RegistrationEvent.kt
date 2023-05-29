package fit.budle.event.customer

import fit.budle.dto.enums.RegisterType

sealed interface RegistrationEvent {
    object GetCode : RegistrationEvent
    object PostCode : RegistrationEvent
    object Login : RegistrationEvent
    data class PostUser(val type: RegisterType) : RegistrationEvent
}