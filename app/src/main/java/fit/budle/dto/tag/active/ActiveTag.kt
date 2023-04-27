package fit.budle.dto.tag.active

interface Tag {
    val tagId: Int
    val tagName: String
}

val ordersTagList = mutableListOf(
    RectangleActiveTag(
        "Все",
        3
    ),
    RectangleActiveTag(
        "Ожидающие",
        0
    ),
    RectangleActiveTag(
        "Принятые",
        1
    ),
    RectangleActiveTag(
        "Отклоненные",
        2
    )
)
