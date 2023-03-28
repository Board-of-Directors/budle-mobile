package fit.budle.models

data class RectangleTag(
    override val tagName: String,
    override var tagId: Int,
    val iconId: Int? = null,
) : Tag

val tagList = mutableListOf(
    RectangleTag(
        "Все",
        0
    ),
    RectangleTag(
        "Рестораны",
        1
    ),
    RectangleTag(
        "Гостиницы",
        2
    ),
    RectangleTag(
        "Развлечения",
        3
    ),
)

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

