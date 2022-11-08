package com.example.budle

import android.media.Image
import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budle.ui.theme.BudleTheme
import com.example.budle.ui.theme.gilroy
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    FirstScreen()
                    //Greeting()
                }
            }
        }
    }
}

data class Button(
    val message: String,
    val color: String,
    val size: Int
)

@Preview
@Composable
fun FirstScreen() {
    Column(
        Modifier.scale(2f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.logo_big),
            contentDescription = "admin icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(40.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Бронируйте места \n в пару кликов",
            fontFamily = gilroy,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Greeting() {
    val array = check()
    Column {
        Text(text = "${array[0]}!")
        Text(text = "${array[1]}!")
        Text(text = "${array[2]}!")
        Text(text = "${array[3]}!")
        Text(text = "${array[4]}!")
        Text(text = "${array[5]}!")
    }
}

fun check(): Array<String?> {
    val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    val url = URL("http://10.0.2.2:8080/users")
    val connection = url.openConnection()
    var array = emptyArray<String?>()
    BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
        var line: String?
        while (inp.readLine().also { line = it } != null) {
            array += line
        }
    }
    return array
}
