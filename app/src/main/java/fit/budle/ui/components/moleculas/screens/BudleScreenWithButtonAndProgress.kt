package fit.budle.ui.components.moleculas.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import fit.budle.ui.components.atoms.headers.BudleNavigationHeader

@Composable
fun BudleScreenWithButtonAndProgress(
    navHostController: NavHostController,
    iconId: Int? = null,
    buttonText: String,
    progress: String? = null,
    onClick: () -> Unit,
    textMessage: String,
    content: @Composable() (() -> Unit)
) {
    BudleScreenWithButton(
        onClick = onClick,
        buttonText = buttonText,
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