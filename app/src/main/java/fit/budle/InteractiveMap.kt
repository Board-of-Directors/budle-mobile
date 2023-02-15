package fit.budle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fit.budle.ui.theme.InteractiveMapTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

class InteractiveMap : ComponentActivity() {
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InteractiveMapTheme {
                val isClicked = remember { mutableStateOf(false) }
                var counter = 1

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(Modifier.fillMaxWidth()){
                        Column(horizontalAlignment = Alignment.CenterHorizontally){
                            LazyColumn {
                                if (isClicked.value) {
                                    counter++
                                }
                                items(counter) {
                                    SetCircle()
                                }
                                isClicked.value = false
                            }
                        }
                        Button(
                            modifier = Modifier.padding(top = 600.dp),
                            onClick = {
                                isClicked.value = true
                            },
                            colors = ButtonDefaults.buttonColors(Color.Black)
                        ) {
                            androidx.compose.material.Text(
                                text = "Добавить ёбаный шар",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                                style = MaterialTheme.typography.button,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

@Composable
fun Text() {
    var offsetX by remember { mutableStateOf(0f) }
    androidx.compose.material.Text(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                }
            ),
        text = "Drag me!"
    )
}

fun Offset.rotateBy(angle: Float): Offset {
    val angleInRadians = angle * PI / 180
    return Offset(
        (x * cos(angleInRadians) - y * sin(angleInRadians)).toFloat(),
        (x * sin(angleInRadians) + y * cos(angleInRadians)).toFloat()
    )
}

@Preview
@Composable
fun SetCircle() {
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {

        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        val isPressed = remember { mutableStateOf(false) }
        val tstColor = if (isPressed.value) Color.Red else Color.Black

        var offset by remember { mutableStateOf(Offset.Zero) }
        var zoom by remember { mutableStateOf(1f) }
        var angle by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            isPressed.value = !isPressed.value
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
                /*
                .pointerInput(Unit) {
                    detectTransformGestures(
                        onGesture = { centroid, pan, gestureZoom, gestureRotate ->
                            val oldScale = zoom
                            val newScale = zoom * gestureZoom
                            offset = (offset + centroid / oldScale).rotateBy(gestureRotate) -
                                    (centroid / newScale + pan / oldScale)
                            zoom = newScale
                            angle += gestureRotate
                        }
                    )
                }
                .graphicsLayer {
                    translationX = -offset.x * zoom
                    translationY = -offset.y * zoom
                    scaleX = zoom
                    scaleY = zoom
                    rotationZ = angle
                    transformOrigin = TransformOrigin(0f, 0f)
                }*/
                .size(100.dp)
                .clip(shape = CircleShape)
                .background(tstColor)
        )
    }*/
}
