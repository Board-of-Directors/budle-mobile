package fit.budle.screens.business.creator.creation_process

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.components.moleculas.screens.BudleInformationScreen
import fit.budle.navigation.routes.NavRoute

@Composable
fun EstablishmentCreationFifthScreen(
    navHostController: NavHostController
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
                    navHostController.popBackStack()
                }
            )
            BudleInformationScreen(
                modifier = Modifier.padding(top = 40.dp),
                onClick = {
                    navHostController.navigate(NavRoute.BusinessMain.route)
                },
                message = "Супер! Заявка подана",
                description = "Мы уже начали обрабатывать\n" +
                        "Ваш запрос. Подождите немного!",
                imageId = R.drawable.success_rocket,
                imageDescription = "Success rocket",
                buttonText = "На главный экран"
            ) {
            }
        }
    }
}