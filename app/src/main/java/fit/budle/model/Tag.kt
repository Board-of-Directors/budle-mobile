package fit.budle.model

interface Tag {
    val tagId: Int
    val tagName: String
}

val ordersTagList = mutableListOf(
    RectangleTag(
        "Все",
        0
    ),
    RectangleTag(
        "Ожидающие",
        1
    ),
    RectangleTag(
        "Принятые",
        2
    ),
    RectangleTag(
        "Отклоненные",
        3
    )
)
