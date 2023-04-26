package fit.budle.dto.worker

import fit.budle.dto.customer_user.User

data class Worker(
    val id: Long,
    val onWork: Boolean,
    val user: User,
)
