package fit.budle.components.moleculas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import fit.budle.components.atoms.BudleButton
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleScreenWithButton(
    navHostController: NavHostController,
    iconId: Int? = null,
    buttonText: String,
    route: String,
    content: @Composable() (() -> Unit)
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
            onClick = {
                navHostController.navigate(route)
            },
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
            .verticalScroll(rememberScrollState()),
    ) {
        content()
    }
}