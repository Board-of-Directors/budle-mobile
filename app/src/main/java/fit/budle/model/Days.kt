package fit.budle.model

data class CircleTag(
    override val tagId: Int,
    override val tagName: String
) : Tag

val days = mutableListOf(
    CircleTag(
        21,
        "Пн"
    ),
    CircleTag(
        22,
        "Вт"
    ),
    CircleTag(
        23,
        "Ср"
    ),
    CircleTag(
        24,
        "Чт"
    ),
    CircleTag(
        25,
        "Пт"
    ),
    CircleTag(
        26,
        "Сб"
    ),
    CircleTag(
        27,
        "Вс"
    )
)

val time = mutableListOf(
    RectangleTag(
        "12:00",
        0
    ),
    RectangleTag(
        "12:30",
        1
    ),
    RectangleTag(
        "13:00",
        2
    ),
    RectangleTag(
        "13:30",
        3
    ),
    RectangleTag(
        "14:00",
        4
    ),
    RectangleTag(
        "14:30",
        5
    ),
    RectangleTag(
        "15:00",
        6
    )
)
