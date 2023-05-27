package fit.budle.ui.util.xml_parser

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XMLParser {

    private val shapes = ArrayList<XMLShape>()
    private var xmlShape: XMLShape? = null

    fun parse(istream: InputStream): List<XMLShape> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(istream, null)

            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.name.equals("path")) {

                    val path = parser.getAttributeValue(0)
                    val coordinates = parseCoordinates(path)

                    xmlShape = XMLShape()
                    xmlShape!!.id = shapes.size + 1
                    xmlShape!!.shapeId = parser.getAttributeValue(2).toInt()
                    xmlShape!!.pathData = path
                    xmlShape!!.offestX = coordinates.first
                    xmlShape!!.offestX = coordinates.second

                    xmlShape!!.fillColor = parser.getAttributeValue(1)
                    /*
                if (parser.attributeCount == 3) {
                    xmlShape!!.fillAlpha = parser.getAttributeValue(2)
                }*/
                    shapes.add(xmlShape!!)
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return shapes
    }

    private fun createNewPath(path: String): String {
        var pos = 0
        for (i in 0..path.length) {
            if (path[i] == 'C') {
                pos = i
                break
            }
        }
        val newPath = path.slice(pos until path.length)
        return "M0 0$newPath"
    }

    private fun parseCoordinates(path: String): Pair<Float, Float> {
        val numbers = path.split(" ")
        val first = numbers[0].removePrefix("M").toFloat()
        val second = numbers[1].split("C", "L")[0].toFloat()
        return Pair(first, second)
    }
}