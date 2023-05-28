package fit.budle.ui.screens.customer

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.RectF
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.graphics.PathParser.createPathFromPathData
import androidx.navigation.NavHostController
import fit.budle.event.customer.MainEvent
import fit.budle.ui.util.xml_parser.XMLParser
import fit.budle.ui.util.xml_parser.XMLShape
import fit.budle.viewmodel.customer.MainViewModel
import fit.budle.viewmodel.customer.OrderCreateViewModel
import moe.tlaster.zoomable.Zoomable
import moe.tlaster.zoomable.rememberZoomableState
import java.io.InputStream
import kotlin.math.roundToInt

@SuppressLint("ResourceType")
@Composable
fun MapScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    orderViewModel: OrderCreateViewModel,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        mainViewModel.onEvent(MainEvent.GetEstablishment)

        val shapes = parseSVG(mainViewModel.establishmentCard.map!!.byteInputStream())
        val state = rememberZoomableState(minScale = 1f, maxScale = 3f)
        val mapOfTables = remember { mutableStateMapOf<XMLShape, Boolean>() }

        SelectSlidebar(tablesMap = mapOfTables, navHostController)
        Zoomable(state = state) {
            Box(
                Modifier
                    .fillMaxSize()
                    .selectableGroup()
            ) {
                ShowMap(
                    mutableCollection = mapOfTables,
                    xmlShapes = shapes,
                    sendSeat = {
                        orderViewModel.selectedSeatId = it.toInt()
                    }
                )
            }
        }
    }
}

@Composable
fun SelectSlidebar(
    tablesMap: MutableMap<XMLShape, Boolean>, navHostController: NavHostController,
) {
    tablesMap.forEach { (table, state) ->
        if (state) {
            ShowSlidebar(xmlShape = table, navHostController)
        }
    }
}

enum class SwipeDirection(val raw: Int) {
    Initial(0),
    Down(1),
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowSlidebar(xmlShape: XMLShape, navHostController: NavHostController) {

    val swipeableState = rememberSwipeableState(SwipeDirection.Initial)

    Column(
        modifier = Modifier
            .offset(y = 20.dp)
            .fillMaxWidth()
            .zIndex(10f)
            .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) },
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .size(100.dp, 10.dp)
                .align(CenterHorizontally)
                .background(color = Gray, shape = RoundedCornerShape(30.dp))
        )
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .height(IntrinsicSize.Min)
                .weight(1f, false)
                .swipeable(
                    state = swipeableState,
                    anchors = mapOf(
                        0f to SwipeDirection.Initial,
                        400f to SwipeDirection.Down,
                    ),
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Vertical
                ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        )
        {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Столик №${xmlShape.id}", textAlign = TextAlign.Start)
                    Text(text = "Свободно", textAlign = TextAlign.End)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Количество мест", textAlign = TextAlign.Start)
                    Text(text = "5 гостей", textAlign = TextAlign.End)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = { navHostController.popBackStack() }) {
                    Text(text = "Забронировать", textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ShowMap(
    mutableCollection: MutableMap<XMLShape, Boolean>,
    xmlShapes: List<XMLShape>,
    sendSeat: (String) -> Unit,
) {

    var selectedItem by remember { mutableStateOf(0) }
    val isSelectedItem: (Int) -> Boolean = { selectedItem == it }
    val onChangeState: (Int) -> Unit = {
        selectedItem = if (!isSelectedItem(it)) it else 0
    }

    xmlShapes.forEach { shape ->
        val isSelected = showShape(
            shape = shape,
            isSelected = isSelectedItem,
            onChangeState = onChangeState
        )
        mutableCollection[shape] = isSelected
        if (isSelected) {
            sendSeat(shape.shapeId.toString())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun showShape(
    shape: XMLShape,
    isSelected: (Int) -> Boolean,
    onChangeState: (Int) -> Unit,
): Boolean {

    val res = transformPath(shape)
    val path = res.first
    val bounds = res.second
    val color = Color(Color.parseColor(shape.fillColor))
    val stroke = if (isSelected(shape.id)) Green else Color(Color.parseColor(shape.fillColor))
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
    return isSelected(shape.id)
}

fun transformPath(shape: XMLShape): Pair<android.graphics.Path, RectF> {

    val path = createPathFromPathData(shape.pathData)
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