package fit.budle.ui.details

import android.view.MotionEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import fit.budle.button.Button
import fit.budle.button.ShowIcon
import fit.budle.button.Status

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BudleTest(result: String) {
    Column {
        Text(text = result)
    }

    val status = remember { mutableStateOf(Status.Active) }
    Button(
        modifier = Modifier
            .padding(10.dp, 320.dp)
            .pointerInteropFilter {
                status.value = if (it.action == MotionEvent.ACTION_DOWN) {
                    Status.Pressed
                } else {
                    Status.Active
                }; true
            },
        status = status.value,
        onClick = {},
        text = "Забронировать",
        showIcon = ShowIcon.False,
        icon = //TODO()
    )
}
