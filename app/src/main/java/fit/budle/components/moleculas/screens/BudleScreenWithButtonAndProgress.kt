package fit.budle.components.moleculas.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.components.moleculas.BudleScreenWithButton

@Composable
fun BudleScreenWithButtonAndProgress(
    navHostController: NavHostController,
    iconId: Int? = null,
    buttonText: String,
    progress: String,
    route: String,
    textMessage: String,
    content: @Composable() (() -> Unit)
) {
    BudleScreenWithButton(
        navHostController = navHostController,
        buttonText = buttonText,
        route = route,
        iconId = iconId
    ) {
        BudleNavigationHeader(
            percent = progress,
            textMessage = textMessage,
            onClick = {
                navHostController.popBackStack()
            }
        )
        content()
    }
}