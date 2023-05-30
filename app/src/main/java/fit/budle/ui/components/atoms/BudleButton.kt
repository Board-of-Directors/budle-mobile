package fit.budle.ui.components.atoms

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fit.budle.R
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconId: Int? = null,
    bottomPadding: Dp = 0.dp,
    topPadding: Dp = 20.dp,
    horizontalPadding: Dp = 30.dp,
    buttonText: String,
    disabledButtonColor: Color = fillPurple,
    activeButtonColor: Color = disabledButtonColor,
    disabledTextColor: Color = mainWhite,
    activeTextColor: Color = disabledTextColor,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonColor = if (isPressed) activeButtonColor else disabledButtonColor
    val contentColor = if (isPressed) activeTextColor else disabledTextColor

    Button(
        modifier = modifier
            .padding(horizontal = horizontalPadding)
            .padding(top = topPadding)
            .padding(bottom = bottomPadding)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(buttonColor)
    ) {
        Row(
            modifier = modifier
                .width(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconId != null) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "Button icon",
                    tint = contentColor
                )
            }
            Text(
                text = buttonText,
                fontSize = 16.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor
            )
        }
    }
}

@Preview
@Composable
fun PreviewBudleButton() {
    BudleButton(
        onClick = {},
        iconId = R.drawable.zap,
        buttonText = "Создать бизнес-аккаунт",
        disabledButtonColor = fillPurple,
        activeButtonColor = fillPurple,
        disabledTextColor = mainWhite,
        activeTextColor = mainWhite
    )
}