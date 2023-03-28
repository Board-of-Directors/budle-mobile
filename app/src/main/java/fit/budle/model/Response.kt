package fit.budle.model

import fit.budle.model.establishment.EstablishmentResponseArray

data class AnswerRegistration(val result: Boolean?, val exception: Exception?)
data class AnswerEstablishment(val result: EstablishmentResponseArray, val exception: Exception?)
data class AnswerCategories(val result: Array<String>, val exception: Exception?)
data class AnswerOrders(val result: Array<Booking>, val exception: Exception?)
data class Exception(val message: String, val type: String)
