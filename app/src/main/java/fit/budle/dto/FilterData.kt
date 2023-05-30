package fit.budle.dto

import fit.budle.dto.tag.active.RectangleActiveTag

object FilterData {
    val establishmentTypeList = listOf(
        "Рестораны", "Отели"
    )
    val workingHourList = listOf(
        RectangleActiveTag("Ежедневно", 0),
        RectangleActiveTag("Пн-Пт", 1),
        RectangleActiveTag("Пн-Сб", 2)
    )
    val characteristicData = listOf(
        RectangleActiveTag("Есть", 0),
        RectangleActiveTag("Отсутствует", 1),
    )
}