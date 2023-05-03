package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.WorkingHour
import fit.budle.dto.events.EstCreationEvent
import fit.budle.dto.tag.subwayStations
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.inputs.dropdown.BudleDropDownMenu
import fit.budle.ui.components.moleculas.day_list.BudleWorkingDaysPickerList
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.viewmodel.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentCreationFourthScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    var daysCount by remember { mutableStateOf(1) }
    var selectedSubwayStation by remember { mutableStateOf("Отсутствует") }
    val selectedWorkingDays = remember { mutableStateListOf<WorkingHour>() }
    Log.d("WORKING_HOURS", selectedWorkingDays.size.toString())

    val onSubwayStationChange: (String) -> Unit = { selectedSubwayStation = it }
    val onWorkingDaysChange: (List<WorkingHour>) -> Unit = {
        selectedWorkingDays.clear()
        for (workingHour in it) {
            if (!selectedWorkingDays.contains(workingHour)) {
                selectedWorkingDays.add(workingHour)
            }
        }
    }

    Surface(Modifier.fillMaxSize()) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "80%",
            onClick = {
                viewModel.onEvent(
                    EstCreationEvent.FourthStep(
                        selectedSubwayStation,
                        selectedWorkingDays
                    )
                )
                navHostController.navigate("fifthStep")
            },
            textMessage = "Создание заведения"
        ) {
            /*budleSingleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                placeHolderColor = mainBlack,
                placeHolder = "Адрес",
                startMessage = "",
                textFieldMessage = "Укажите адрес на карте",
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.map),
                            contentDescription = "Map",
                            tint = fillPurple
                        )
                    }
                }
            )*/
            BudleDropDownMenu(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onValueChange = { onSubwayStationChange(it) },
                items = subwayStations,
                placeHolder = "Метро",
                startMessage = "Укажите станцию метро",
                isError = false,
                selectedItem = ""
            )
            BudleWorkingDaysPickerList(
                items = daysCount,
                onValueChange = {
                    onWorkingDaysChange(it)
                }
            )
            BudleButton(
                bottomPadding = 100.dp,
                onClick = { daysCount++ },
                buttonText = "Добавить ещё",
                disabledButtonColor = lightBlue,
                disabledTextColor = fillPurple,
                iconId = R.drawable.plus,
                horizontalPadding = 0.dp,
            )
        }
    }
}