package fit.budle.ui.details

import android.view.MotionEvent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import fit.budle.testbutton.Status
import fit.budle.testbutton.TestButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BudleTest(result: String) {
    Column {
        Text(text = result)
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val color = if (isPressed) Status.Active else Status.Pressed
    val status = remember { mutableStateOf(Status.Active) }
    TestButton(modifier = Modifier.padding(10.dp, 320.dp).pointerInteropFilter {
        when (it.action) {
            MotionEvent.ACTION_DOWN -> {
                status.value = Status.Pressed }

            MotionEvent.ACTION_UP  -> {
                status.value = Status.Active }
        }
        true}, status = status.value, onClick = {}, text = "Трахать!")
}
