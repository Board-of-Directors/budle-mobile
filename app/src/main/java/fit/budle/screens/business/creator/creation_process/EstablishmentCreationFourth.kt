package fit.budle.screens.business.creator.creation_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.inputs.BudleDropDownMenu
import fit.budle.components.atoms.inputs.text_inputs.BudleSingleLineInput
import fit.budle.components.atoms.inputs.photo_picker.BudleMultiplePhotoInput
import fit.budle.components.data.TagType
import fit.budle.components.moleculas.BudleBlockWithHeader
import fit.budle.components.moleculas.budleMultiSelectableTagList
import fit.budle.components.moleculas.budleTagList
import fit.budle.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.models.days
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentCreationFourthScreen(
    navHostController: NavHostController
) {

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
        BudleBlockWithHeader(headerText = "Дни работы") {
            budleMultiSelectableTagList(
                modifier = Modifier.padding(top = 10.dp),
                showDate = false,
                tagList = days,
                tagType = TagType.CIRCLE
            )
        }
    }
}