package fit.budle.ui.util.xml_parser

class XMLShape {

    lateinit var pathData: String
    lateinit var fillColor: String
    var id: Int = 0
    var fillAlpha: String? = null
    var offestX: Float = 0f
    var offestY: Float = 0f
    var shapeId: Int = 0

    override fun toString(): String {
        return "Color = $fillColor\n Alpha = $fillAlpha\n Path = $pathData"
    }
}