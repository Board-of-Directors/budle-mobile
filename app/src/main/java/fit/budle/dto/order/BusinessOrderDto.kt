package fit.budle.dto.order

import fit.budle.di.establishment.etsablishment_type.EstablishmentBasicDto

data class BusinessOrderDto(
    val date: String,
    val endTime: String,
    val startTime: String,
    val establishment: EstablishmentBasicDto,
    val guestCount: Int,
    val id: Int,
    val status: Int,
    val userId: Int
)
