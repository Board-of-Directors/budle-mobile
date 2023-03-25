package fit.budle.components.moleculas

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            content()
        }
        Row(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .fillMaxWidth()
                .weight(1f, false)
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
    }
}