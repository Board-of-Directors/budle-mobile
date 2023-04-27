package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import fit.budle.dto.events.EstCreationEvent
import fit.budle.dto.tag.establishmentCreationTags
import fit.budle.dto.tag.establishmentCreationType
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.components.moleculas.tag_list.BudleMultiColumnTagList
import fit.budle.ui.components.moleculas.tag_list.budleMultiColumnTagList
import fit.budle.viewmodel.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentCreationSecondScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    val selectedTypeId = remember { mutableStateOf(-1) }
    var selectedTypeName by remember { mutableStateOf("") }
    val onIdChange: (Int) -> (Unit) = {
        selectedTypeId.value = it
    }

    for (list in establishmentCreationType) {
        for (tag in list) {
            if (tag.tagId == selectedTypeId.value) {
                selectedTypeName = tag.tagName
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "40%",
            onClick = {
                viewModel.onEvent(EstCreationEvent.SecondStep(selectedTypeName,))
                navHostController.navigate("thirdStep")
            },
            textMessage = "Создание заведения"
        ) {
            SecondEstablishmentCreationType(
                selectedType = selectedTypeId,
                onValueChange = onIdChange
            )

            /* // TODO Олег не сделал подкатегории
            SecondEstablishmentCreationSubtype(
                type = selectedType
            ) */

            SecondEstablishmentCreationInfoTags()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SecondEstablishmentCreationType(
    selectedType: MutableState<Int>,
    onValueChange: (Int) -> (Unit)
) {
    BudleBlockWithHeader(headerText = "Тип заведения") {
        val selectedItem = budleMultiColumnTagList(
            selectedItem = selectedType,
            tags = establishmentCreationType
        )
        onValueChange(selectedItem)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SecondEstablishmentCreationSubtype(
    type: MutableState<String>,
) {
    val selectedSubtype = remember { mutableStateOf("") }
    when (type.value) {
        "Рестораны" -> {
            BudleBlockWithHeader(headerText = "Вид кухни") {
                /*
                BudleMultiColumnTagList(
                    selectedItem = selectedSubtype,
                    tags = restaurantsType
                )
                */
            }
        }
        "Гостиницы" -> {
            BudleBlockWithHeader(headerText = "Количество звезд") {
                /*
                BudleMultiColumnTagList(
                    selectedItem = selectedSubtype,
                    tags = startsType
                )
                 */
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SecondEstablishmentCreationInfoTags() {

    val selectedInfoTag = remember { mutableStateOf(-1) }

    BudleBlockWithHeader(headerText = "Тэги заведения") {
        BudleMultiColumnTagList(
            selectable = true,
            selectedItem = selectedInfoTag,
            tags = establishmentCreationTags
        )
    }
}