package fit.budle.ui.util

import javax.xml.parsers.DocumentBuilderFactory

fun parseSvgAttributes(svgString: String): String {

    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    val xml = builder.parse(svgString.byteInputStream())
    val svgElement = xml.documentElement

    return svgElement.getAttribute("d")

}