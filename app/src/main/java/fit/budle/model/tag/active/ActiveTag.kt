package fit.budle.model.tag.active

interface Tag {
    val tagId: Int
    val tagName: String
}

val ordersTagList = mutableListOf(
    RectangleActiveTag(
        "Все",
        0
    ),
    RectangleActiveTag(
        "Ожидающие",
        1
    ),
    RectangleActiveTag(
        "Принятые",
        2
    ),
    RectangleActiveTag(
        "Отклоненные",
        3
    )
)
