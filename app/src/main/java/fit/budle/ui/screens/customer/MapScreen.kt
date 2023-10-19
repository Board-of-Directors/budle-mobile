package fit.budle.ui.screens.customer

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.customer.OrderCreateEvent
import fit.budle.ui.components.atoms.BudleSlidebar
import fit.budle.ui.components.organism.BudleBookingMap
import fit.budle.ui.theme.backgroundSuccess
import fit.budle.ui.theme.textGray
import fit.budle.ui.util.SVGParser.Companion.parseSVG
import fit.budle.ui.util.xml_parser.XMLShape
import fit.budle.viewmodel.customer.MainViewModel
import fit.budle.viewmodel.customer.OrderCreateViewModel
import moe.tlaster.zoomable.Zoomable
import moe.tlaster.zoomable.rememberZoomableState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MapScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    orderViewModel: OrderCreateViewModel,
) {
    if (mainViewModel.establishmentCard.map != null) {

        val shapes = parseSVG(mainViewModel.establishmentCard.map!!.byteInputStream())
        val state = rememberZoomableState(minScale = 1f, maxScale = 3f)

        val mapOfTables = remember { mutableStateMapOf<XMLShape, Boolean>() }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navHostController.popBackStack()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_left_gray),
                        contentDescription = "Arrow Left",
                        tint = textGray
                    )
                }
                Text(
                    text = "Все свободны",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium,
                    color = backgroundSuccess
                )
            }
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                val selectedTable = getActiveTable(mapOfTables)
                if (selectedTable != null) {
                    BudleSlidebar(
                        xmlShape = selectedTable,
                        onClick = {
                            orderViewModel.onEvent(OrderCreateEvent.SetSpot)
                            orderViewModel.onEvent(
                                OrderCreateEvent.PostOrder(
                                    mainViewModel.establishmentCard.id
                                )
                            )
                            navHostController.navigate("main")
                        },
                        orderViewModel = orderViewModel,
                        mainViewModel = mainViewModel
                    )
                } else Log.d("SELECTED_TABLE", "IS NULL")
                Zoomable(state = state) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .selectableGroup()
                    ) {
                        BudleBookingMap(
                            mutableCollection = mapOfTables,
                            xmlShapes = shapes,
                            onChangeState = {
                                orderViewModel.selectedSeatId =
                                    if (orderViewModel.selectedSeatId != it) it else -1
                                for (table in mapOfTables) {
                                    Log.d(table.key.id.toString(), table.value.toString())
                                }
                            },
                            isSelected = {
                                orderViewModel.selectedSeatId == it
                            }
                        )
                    }
                }
            }
        }
    }
}

fun getActiveTable(
    tablesMap: MutableMap<XMLShape, Boolean>,
): XMLShape? {
    tablesMap.forEach { (table, state) ->
        if (state) return table
    }
    return null
}