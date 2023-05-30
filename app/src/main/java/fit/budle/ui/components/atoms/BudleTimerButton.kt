package fit.budle.ui.components.atoms

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray

@Composable
fun BudleTimerButton(
    curTicks: Int, onClick: () -> Unit, bottomPadding: Dp = 0.dp,
    topPadding: Dp = 20.dp,
    horizontalPadding: Dp = 30.dp,
) {

    Button(
        onClick = onClick,
        colors = if (curTicks == 0) (ButtonDefaults.buttonColors(containerColor = fillPurple)) else ButtonDefaults.buttonColors(
            containerColor = backgroundLightBlue
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
            .padding(top = topPadding)
            .padding(bottom = bottomPadding)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
    ) {
        Text(
            text = if (curTicks != 0) "Новый код - $curTicks" else "Отправить код",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = if (curTicks != 0) textGray else Color.White
        )
    }
}
