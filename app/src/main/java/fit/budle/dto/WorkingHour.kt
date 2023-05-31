package fit.budle.dto

data class WorkingHour(
    val days: List<String>,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
)