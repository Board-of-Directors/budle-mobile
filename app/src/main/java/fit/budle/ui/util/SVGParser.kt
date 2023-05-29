package fit.budle.ui.util

import android.graphics.RectF
import androidx.core.graphics.PathParser
import fit.budle.ui.util.xml_parser.XMLParser
import fit.budle.ui.util.xml_parser.XMLShape
import java.io.InputStream

class SVGParser {
    companion object {
        fun transformPath(shape: XMLShape): Pair<android.graphics.Path, RectF> {

            val path = PathParser.createPathFromPathData(shape.pathData)
            val bounds = RectF()
            val matrix = android.graphics.Matrix()

            path.computeBounds(bounds, true)
            path.offset(-bounds.left, -bounds.top)

            matrix.setScale(3f, 3f)
            path.transform(matrix)

            return Pair(path, bounds)
        }

        fun parseSVG(istream: InputStream): List<XMLShape> {
            return XMLParser().parse(istream)
        }
    }
}