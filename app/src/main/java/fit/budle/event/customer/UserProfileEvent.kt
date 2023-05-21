package fit.budle.event.customer

import fit.budle.dto.customer_user.RequestUser

sealed interface UserProfileEvent {
    data class PostBusinessLoginEvent(val requestUser: RequestUser) : UserProfileEvent
}