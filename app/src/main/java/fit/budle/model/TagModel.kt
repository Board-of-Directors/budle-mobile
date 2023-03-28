package fit.budle.model

data class RectangleTag(
    override val tagName: String,
    override var tagId: Int,
    val iconId: Int? = null,
) : Tag
