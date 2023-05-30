package fit.budle.dto.establishment

data class WorkingHoursDto(
    var fromTime: String = "",
    var toTime: String = "",
    var daysOfWork: List<String> = listOf(),
)

data class RequestWorkingHoursDto(
    var startTime: String = "",
    var endTime: String = "",
    var days: List<String> = listOf(),
)