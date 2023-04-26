package fit.budle.dto.response

import fit.budle.dto.establishment.EstablishmentResponseArray
import fit.budle.dto.order.Booking

sealed interface EstablishmentResponse {
    data class AnswerRegistration(val result: Boolean?, val exception: Exception?) : EstablishmentResponse
    data class AnswerEstablishment(
        val result: EstablishmentResponseArray,
        val exception: Exception?
    ) : EstablishmentResponse
    data class AnswerCategories(val result: Array<String>, val exception: Exception?) : EstablishmentResponse
    data class AnswerOrders(val result: Array<Booking>, val exception: Exception?) : EstablishmentResponse
}