package fit.budle.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(textProvider: () -> String) {
    Text(text = textProvider())
}
