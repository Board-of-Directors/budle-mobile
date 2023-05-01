package fit.budle.ui.screens.business.creator.creator_process

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.events.EstCreationEvent
import fit.budle.ui.components.atoms.headers.BudleNavigationHeader
import fit.budle.ui.components.screens.BudleInformationScreen
import fit.budle.viewmodel.EstCreationViewModel

@Composable
fun EstablishmentCreationFifthScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel = hiltViewModel()
) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            BudleNavigationHeader(
                percent = "100%",
                textMessage = "Создание заведения",
                onClick = {
                    navHostController.navigate("ownerMain")
                }
            )
            BudleInformationScreen(
                modifier = Modifier.padding(top = 40.dp),
                onClick = {
                    viewModel.onEvent(EstCreationEvent.CreateEstablishment)
                },
                message = "Супер! Заявка подана",
                description = "Мы уже начали обрабатывать\n" +
                        "Ваш запрос. Подождите немного!",
                imageId = R.drawable.success_rocket,
                imageDescription = "Success rocket",
                buttonText = "Создать заведение"
            ) {
            }
        }
    }
}