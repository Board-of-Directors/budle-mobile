package fit.budle.dto.tag.active

data class ActiveCircleTag(
    override val tagId: Int,
    override val tagName: String
) : Tag

val days = mutableListOf(
    ActiveCircleTag(
        21,
        "Пн"
    ),
    ActiveCircleTag(
        22,
        "Вт"
    ),
    ActiveCircleTag(
        23,
        "Ср"
    ),
    ActiveCircleTag(
        24,
        "Чт"
    ),
    ActiveCircleTag(
        25,
        "Пт"
    ),
    ActiveCircleTag(
        26,
        "Сб"
    ),
    ActiveCircleTag(
        27,
        "Вс"
    )
)

