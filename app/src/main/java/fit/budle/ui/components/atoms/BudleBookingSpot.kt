package fit.budle.ui.components.atoms

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.dp
import fit.budle.ui.util.SVGParser
import fit.budle.ui.util.xml_parser.XMLShape

@Composable
fun BudleBookingSpot(
    shape: XMLShape,
    isSelected: (Int) -> Boolean,
    onChangeState: (Int) -> Unit,
) {

    val res = SVGParser.transformPath(shape)
    val path = res.first
    val bounds = res.second
    val stroke =
        if (isSelected(shape.id)) Color.Green else Color(android.graphics.Color.parseColor(shape.fillColor))
    val alpha = if (shape.fillAlpha == null) 1f else shape.fillAlpha!!.toFloat()

    Canvas(
        modifier = Modifier
            .absolutePadding((bounds.left).dp, (bounds.top).dp)
            .width((bounds.width()).dp)
            .height((bounds.height()).dp)
            .selectable(
                selected = isSelected(shape.id),
                onClick = { onChangeState(shape.id) },
            )
    ) {
        drawPath(
            path = path.asComposePath(),
            color = stroke,
            alpha = alpha
        )
    }
}