package fit.budle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fit.budle.ui.theme.InteractiveMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InteractiveMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LongPressDraggable(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 10.dp)
                        ) {
                            items(items = drags) { shape ->
                                DragShape(color = Color.Red)
                            }
                        }
                        PersonListContainer()
                    }
                }
            }
        }
    }
}

@Composable
fun BoxScope.PersonListContainer() {
    LazyRow(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth()
            .background(
                Color.LightGray,
                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
            )
            .padding(vertical = 10.dp)
            .align(Alignment.BottomCenter),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        items(items = drops) {
            DropShape()
        }
    }
}

@Composable
fun DropShape() {
    val shapes = remember {
        mutableStateMapOf<Int, Shape>()
    }
    DropTarget<Shape>(
        modifier = Modifier
            .padding(6.dp)
            .width(width = 120.dp)
            .fillMaxHeight(0.8f)
    ) { isInBound, shape ->
        val bgColor = if (isInBound) {
            Color.Red
        } else {
            Color.White
        }

        shape?.let {
            if (isInBound)
                shapes[shape.id] = shape
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                .background(
                    bgColor,
                    RoundedCornerShape(16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Circle(color = Color.Blue)
        }
    }
}

@Composable
fun Circle(color: androidx.compose.ui.graphics.Color) {
    Box(modifier = Modifier
        .size(60.dp)
        .clip(shape = CircleShape)
        .background(color = color))
}

@Composable
fun DragShape(color: androidx.compose.ui.graphics.Color) {
    DragTarget(modifier = Modifier.size(60.dp), dataToDrop = drags) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape)
                .background(color = color)
        )
    }
}
