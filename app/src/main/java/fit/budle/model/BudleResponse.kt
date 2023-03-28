package fit.budle.model

data class AnswerRegistration(val result: Boolean?, val exception: Exception?)
data class Exception(val message: String, val type: String)
data class AnswerEstablishment(val result: EstablishmentResult, val exception: Exception?)

data class AnswerCategories(val result: Array<String>, val exception: Exception?)
data class AnswerOrder(val result: Boolean?, val exception: Exception?)

data class AnswerOrders(val result: Array<Booking>, val exception: Exception?)


