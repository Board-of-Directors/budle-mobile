package fit.budle.dto.order

data class ScheduleDay(
    val dayName: String,
    val monthName: String,
    val dayNumber: String,
    val times: Array<String>
)
