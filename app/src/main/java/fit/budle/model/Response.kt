package fit.budle.model

import fit.budle.model.establishment.EstablishmentResponseArray

sealed interface Response {
    data class AnswerRegistration(val result: Boolean?, val exception: Exception?) : Response
    data class AnswerEstablishment(
        val result: EstablishmentResponseArray,
        val exception: Exception?
    ) : Response

    data class AnswerCategories(val result: Array<String>, val exception: Exception?) : Response
    data class AnswerOrders(val result: Array<Booking>, val exception: Exception?) : Response
    data class Exception(val message: String, val type: String) : Response
}
