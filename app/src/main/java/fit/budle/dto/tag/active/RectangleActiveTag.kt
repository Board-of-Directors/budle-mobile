package fit.budle.dto.tag.active

data class RectangleActiveTag(
    override val tagName: String,
    override var tagId: Int,
    val iconId: Int? = null,
) : Tag


val time = mutableListOf(
    RectangleActiveTag(
        "12:00",
        0
    ),
    RectangleActiveTag(
        "12:30",
        1
    ),
    RectangleActiveTag(
        "13:00",
        2
    ),
    RectangleActiveTag(
        "13:30",
        3
    ),
    RectangleActiveTag(
        "14:00",
        4
    ),
    RectangleActiveTag(
        "14:30",
        5
    ),
    RectangleActiveTag(
        "15:00",
        6
    )
)
