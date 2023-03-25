package fit.budle.models

data class RectangleTag(
    override val tagName: String,
    override var tagId: Int,
    override var iconId: Int? = null,
) : Tag

val tagList = mutableListOf<Tag>(
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

