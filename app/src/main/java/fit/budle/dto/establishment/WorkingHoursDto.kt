package fit.budle.dto.establishment

data class WorkingHoursDto(
    var fromTime: String = "",
    var toTime: String = "",
    var daysOfWork: List<String> = listOf()
)
