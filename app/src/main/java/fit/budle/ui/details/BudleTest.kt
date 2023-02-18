package fit.budle.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BudleTest(result: String) {
    Column {
        Text(text = result)
    }
}
