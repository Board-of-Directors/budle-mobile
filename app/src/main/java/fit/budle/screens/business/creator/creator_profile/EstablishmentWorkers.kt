package fit.budle.screens.business.creator.creator_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.BudleAddWorkerPopup
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.inputs.text_inputs.BudleNumberInput
import fit.budle.components.atoms.inputs.text_inputs.BudleSingleLineInput
import fit.budle.components.data.NumberDefaults
import fit.budle.components.moleculas.card_lists.BudleCreatorWorkerCardList
import fit.budle.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.models.BusinessEstablishmentModel
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun EstablishmentWorkersScreen(
    navHostController: NavHostController,
    establishment: BusinessEstablishmentModel
) {

    var isPopup by remember { mutableStateOf(false) }
    val onClick: () -> Unit = { isPopup = false }

    Surface(modifier = Modifier.fillMaxSize()) {
        if (isPopup) {
            BudleAddWorkerPopup(
                onClick = onClick
            )
        }
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Добавить работника",
            iconId = R.drawable.plus,
            onClick = {
                isPopup = true
            },
            textMessage = "Сотрудники"
        ) {
            BudleCreatorWorkerCardList(workerList = establishment.workers)
        }
    }
}