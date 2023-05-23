package fit.budle.ui.components.moleculas.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleScreenWithButton(
    onClick: () -> Unit,
    iconId: Int? = null,
    buttonText: String,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(bottom = 30.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .zIndex(10f),
        verticalAlignment = Alignment.Bottom
    ) {
        BudleButton(
            onClick = onClick,
            horizontalPadding = 0.dp,
            iconId = iconId,
            buttonText = buttonText,
            disabledButtonColor = fillPurple,
            disabledTextColor = mainWhite,
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
            .padding(horizontal = 20.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        content()
    }
}