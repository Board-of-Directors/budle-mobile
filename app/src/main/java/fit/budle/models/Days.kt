package fit.budle.models

data class Tag(
    val tagId: Int,
    val tagName: String,
    val iconId: Int? = null
)

val days = mutableListOf(
    Tag(
        21,
        "Пн"
    ),
    Tag(
        22,
        "Вт"
    ),
    Tag(
        23,
        "Ср"
    ),
    Tag(
        24,
        "Чт"
    ),
    Tag(
        25,
        "Пт"
    ),
    Tag(
        26,
        "Сб"
    ),
    Tag(
        27,
        "Вс"
    )
)

val time = mutableListOf(
    Tag(
        0,
        "12:00",
        ),
    Tag(
        1,
        "12:30",
    ),
    Tag(
        2,
        "13:00",
    ),
    Tag(
        3,
        "13:30",
    ),
    Tag(
        4,
        "14:00",
    ),
    Tag(
        5,
        "14:30",
    ),
    Tag(
        6,
        "15:00",
    )
)