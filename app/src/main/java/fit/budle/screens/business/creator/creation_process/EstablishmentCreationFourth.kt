package fit.budle.screens.business.creator.creation_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.inputs.BudleDropDownMenu
import fit.budle.components.atoms.inputs.text_inputs.BudleSingleLineInput
import fit.budle.components.moleculas.BudleWorkingDaysPicker
import fit.budle.components.moleculas.BudleWorkingDaysPickerList
import fit.budle.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentCreationFourthScreen(
    navHostController: NavHostController
) {
    Surface(Modifier.fillMaxSize()) {
        var daysCount by remember { mutableStateOf(1) }
        val subway = remember {
            mutableListOf(
                "Отсутствует",
                "м. Красный проспект",
                "м. Октябрьская",
                "м. Сибирская",
                "м. Маршала Покрышкина"
            )
        }

        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "80%",
            route = NavRoute.EstablishmentCreationFifth.route,
            textMessage = "Создание заведения"
        ) {
            BudleSingleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                placeHolderColor = mainBlack,
                placeHolder = "Адрес",
                startMessage = "",
                textFieldMessage = "Укажите адрес на карте",
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.map),
                            contentDescription = "Map",
                            tint = fillPurple
                        )
                    }
                }
            )
            BudleDropDownMenu(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                items = subway,
                placeHolder = "Метро",
                startMessage = "Укажите станцию метро"
            )
            BudleWorkingDaysPickerList(daysCount)
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