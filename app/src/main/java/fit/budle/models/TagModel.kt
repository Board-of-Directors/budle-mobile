package fit.budle.models

data class Tag(
    val name: String,
    var tagId: Int
)

val tagList = mutableListOf(
    Tag(
        "Все",
        0
    ),
    Tag(
        "Рестораны",
        1
    ),
    Tag(
        "Гостиницы",
        2
    ),
    Tag(
        "Развлечения",
        3
    ),
)

