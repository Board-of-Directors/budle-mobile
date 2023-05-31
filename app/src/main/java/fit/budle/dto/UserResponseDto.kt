package fit.budle.dto

data class UserResponseDto(
    val accountNonExpired: Boolean = false,
    val accountNonLocked: Boolean = false,
    val authorities: List<Authoritiy>? = null,
    val credentialsNonExpired: Boolean = false,
    val enabled: Boolean = false,
    val id: Int = 0,
    val password: String = "",
    val phoneNumber: String = "",
    val username: String = "",
)

data class Authoritiy(
    val authority: String,
)