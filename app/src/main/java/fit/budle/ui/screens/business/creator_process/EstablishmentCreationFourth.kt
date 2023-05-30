package fit.budle.ui.screens.business.creator_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.tag.subwayStations
import fit.budle.event.business.EstCreationEvent
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.inputs.dropdown.BudleDropDownMenu
import fit.budle.ui.components.moleculas.day_list.business.BudleWorkingDaysPickerList
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.viewmodel.business.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationFourthScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {
    Surface(Modifier.fillMaxSize()) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "80%",
            onClick = {
                viewModel.onEvent(EstCreationEvent.FourthStep)
                navHostController.navigate("mapStep")
            },
            textMessage = "Создание заведения"
        ) {
            BudleDropDownMenu(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onValueChange = { viewModel.selectedAddress = it },
                items = subwayStations,
                placeHolder = "Метро",
                startMessage = "Укажите станцию метро",
                isError = false,
                selectedItem = viewModel.selectedAddress
            )
            BudleWorkingDaysPickerList(
                daysCount = viewModel.blocksCount,
                onValueChange = { day, workingHoursDto ->
                    viewModel.selectedWorkingHours[day] = workingHoursDto
                },
                selectedItems = viewModel.selectedWorkingHours
            )
            BudleButton(
                bottomPadding = 100.dp,
                onClick = { viewModel.blocksCount++ },
                buttonText = "Добавить ещё",
                disabledButtonColor = lightBlue,
                disabledTextColor = fillPurple,
                iconId = R.drawable.plus,
                horizontalPadding = 0.dp,
            )
        }
    }
}