package fit.budle.ui.screens.business.creator.creator_profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.events.WorkerEvent
import fit.budle.ui.components.atoms.BudleAddWorkerPopup
import fit.budle.ui.components.moleculas.card_lists.BudleCreatorWorkerCardList
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.viewmodel.CreatorViewModel

@Composable
fun EstablishmentWorkersScreen(
    navHostController: NavHostController,
    viewModel: CreatorViewModel = hiltViewModel()
) {
    var isPopup by remember { mutableStateOf(false) }
    val onClick: () -> Unit = { isPopup = false }

    Surface(modifier = Modifier.fillMaxSize()) {
        if (isPopup) {
            BudleAddWorkerPopup(
                onClose = onClick,
                onValueChange = {
                    viewModel.onEvent(WorkerEvent.AddWorker(
                        2, it
                    ))
                }
            )
        }
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Добавить работника",
            iconId = R.drawable.plus,
            onClick = { isPopup = true },
            textMessage = "Сотрудники"
        ) {
            BudleCreatorWorkerCardList(
                workerList = viewModel.state.toMutableList(),
                onValueChange = {
                    viewModel.onEvent(WorkerEvent.DeleteWorker(2, it.id))
                }
            )
            viewModel.onEvent(WorkerEvent.GetWorker)
        }
    }
}