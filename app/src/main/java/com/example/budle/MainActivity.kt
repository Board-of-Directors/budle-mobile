package com.example.budle

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.budle.ui.theme.BudleTheme
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
                    Greeting()
                }
            }
        }
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
