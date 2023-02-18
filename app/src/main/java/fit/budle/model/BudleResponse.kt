package fit.budle.model

data class AnswerRegistration(val result: Boolean?, val exception: Exception?)
data class Exception(val message: String, val type: String)
data class AnswerEstablishment(val result: EstablishmentResult, val exception: Exception?)
