package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.dto.events.EstCreationEvent
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.dto.tag.active.Tag
import fit.budle.dto.tag.establishmentCreationCategoryList
import fit.budle.dto.tag.establishmentCreationTags
import fit.budle.ui.components.atoms.inputs.dropdown.BudleDropDownMenu
import fit.budle.ui.components.atoms.inputs.dropdown.BudleMultiSelectableDropDownMenu
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.viewmodel.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentCreationSecondScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    var selectedCategory by remember { mutableStateOf("") }
    var selectedVariant by remember { mutableStateOf("") }
    val selectedTagList = remember { mutableStateListOf<String>() }

    var buttonClicked by remember { mutableStateOf(false) }
    var emptyCategoryError by remember { mutableStateOf(true) }

    val onCategoryChange: (String) -> Unit = { selectedCategory = it }
    val onVariantChange: (String) -> Unit = { selectedVariant = it }

    var previousVariantState by remember { mutableStateOf(viewModel.variantList) }

    val onTagSetChange: (String) -> Unit = {
        if (selectedTagList.contains(it)) {
            selectedTagList.remove(it)
        } else {
            selectedTagList.add(it)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(EstCreationEvent.GetVariantList(selectedCategory))
        viewModel.onEvent(EstCreationEvent.GetCategoryListEvent)
        viewModel.onEvent(EstCreationEvent.GetTagListEvent)
    }

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
                    viewModel.onEvent(EstCreationEvent.SecondStep(selectedCategory,
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
                    onCategoryChange(it)
                    emptyCategoryError = selectedCategory == ""
                },
                items = viewModel.categoryList,
                placeHolder = "Тип заведения",
                startMessage = "Выберите тип заведения"
            )
            if (viewModel.variantList != previousVariantState) {
                viewModel.variantList = previousVariantState
                BudleDropDownMenu(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    isError = false,
                    onValueChange = {
                        onVariantChange(it)
                    },
                    items = viewModel.variantList,
                    placeHolder = "Категория заведения",
                    startMessage = "Выберите категорию заведения"
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