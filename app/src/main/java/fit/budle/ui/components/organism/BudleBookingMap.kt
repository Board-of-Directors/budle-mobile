package fit.budle.ui.components.organism

import androidx.compose.runtime.Composable
import fit.budle.ui.components.atoms.BudleBookingSpot
import fit.budle.ui.util.xml_parser.XMLShape

@Composable
fun BudleBookingMap(
    mutableCollection: MutableMap<XMLShape, Boolean>,
    onChangeState: (Int) -> Unit,
    isSelected: (Int) -> Boolean,
    xmlShapes: List<XMLShape>,
) {
    xmlShapes.forEach { shape ->
        BudleBookingSpot(
            shape = shape,
            isSelected = isSelected,
            onChangeState = onChangeState
        )
        mutableCollection[shape] = isSelected(shape.id)
    }
}