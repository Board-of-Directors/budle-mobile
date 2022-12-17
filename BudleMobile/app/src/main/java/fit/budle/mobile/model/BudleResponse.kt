package fit.budle.mobile.model

data class Answer(val result: Boolean?, val exception: Exception?)
data class Exception(val message: String, val type: String)
