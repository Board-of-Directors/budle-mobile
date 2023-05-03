package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.dto.events.EstCreationEvent
import fit.budle.ui.components.atoms.inputs.dropdown.BudleDropDownMenu
import fit.budle.ui.components.atoms.inputs.dropdown.BudleMultiSelectableDropDownMenu
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.util.SubcategoryChanger
import fit.budle.viewmodel.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentCreationSecondScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    val selectedTagList = remember { mutableStateListOf<String>() }

    var buttonClicked by remember { mutableStateOf(false) }
    var emptyCategoryError by remember { mutableStateOf(true) }

    val onTagSetChange: (String) -> Unit = {
        if (selectedTagList.contains(it)) {
            selectedTagList.remove(it)
        } else {
            selectedTagList.add(it)
        }
    }

    viewModel.onEvent(EstCreationEvent.GetCategoryListEvent)
    viewModel.onEvent(EstCreationEvent.GetTagListEvent)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "40%",
            onClick = {
                buttonClicked = true
                if (!emptyCategoryError) {
                    viewModel.onEvent(EstCreationEvent.SecondStep(
                        viewModel.establishmentDTO.category!!,
                        selectedTagList.toList()))
                    navHostController.navigate("thirdStep")
                }
            },
            textMessage = "Создание заведения"
        ) {
            BudleDropDownMenu(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                isError = if (buttonClicked) emptyCategoryError else false,
                onValueChange = {
                    viewModel.establishmentDTO.category = it
                    viewModel.onEvent(EstCreationEvent.GetVariantList(
                        viewModel.establishmentDTO.category!!
                    ))
                    emptyCategoryError = viewModel.establishmentDTO.category == ""
                },
                selectedItem = viewModel.establishmentDTO.category,
                items = viewModel.testCategoryMap.keys.toList(),
                placeHolder = "Тип заведения",
                startMessage = "Выберите тип заведения"
            )
            if (viewModel.variantList.isNotEmpty()) {
                BudleDropDownMenu(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    isError = false,
                    onValueChange = {
                        SubcategoryChanger.SetSubcategory(viewModel,"name",it)
                    },
                    selectedItem = SubcategoryChanger.GetSubcategoryValue(viewModel,"name"),
                    items = viewModel.variantList,
                    placeHolder = "Подкатегория",
                    startMessage = "Выберите подкатегорию"
                )
            }
            BudleMultiSelectableDropDownMenu(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onValueChange = { onTagSetChange(it) },
                items = viewModel.tagList,
                placeHolder = "Теги заведения",
                startMessage = "Выберите теги заведения"
            )
        }
    }
}