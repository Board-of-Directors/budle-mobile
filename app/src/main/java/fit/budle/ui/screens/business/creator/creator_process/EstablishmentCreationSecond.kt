package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.event.business.EstCreationEvent
import fit.budle.ui.components.atoms.inputs.dropdown.BudleDropDownMenu
import fit.budle.ui.components.atoms.inputs.dropdown.BudleMultiSelectableDropDownMenu
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.util.SubcategoryChanger
import fit.budle.viewmodel.business.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationSecondScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    var buttonClicked by remember { mutableStateOf(false) }
    var emptyCategoryError by remember { mutableStateOf(false) }

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
                    viewModel.onEvent(EstCreationEvent.SecondStep)
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
                    viewModel.selectedCategory = it
                    viewModel.onEvent(EstCreationEvent.GetVariantList)
                    emptyCategoryError = viewModel.selectedCategory.isEmpty()
                },
                selectedItem = viewModel.selectedCategory,
                items = viewModel.categoryList,
                placeHolder = "Тип заведения",
                startMessage = "Выберите тип заведения"
            )
            if (viewModel.variantList.isNotEmpty() &&
                viewModel.variantList.containsKey(viewModel.selectedCategory) &&
                viewModel.variantList[viewModel.selectedCategory]!!.fieldName != null
            ) {
                viewModel.variantList[viewModel.selectedCategory]?.let {
                    val fieldName = it.fieldName!!
                    val variants = it.variants!!
                    val headerName = it.headerName!!
                    BudleDropDownMenu(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        isError = false,
                        onValueChange = {
                            SubcategoryChanger.SetSubcategory(viewModel, fieldName, it)
                            viewModel.selectedVariant[fieldName] =
                                SubcategoryChanger.GetSubcategoryValue(viewModel, fieldName)
                        },
                        selectedItem = viewModel.selectedVariant[fieldName],
                        items = variants,
                        placeHolder = headerName,
                        startMessage = "Выберите ${it.headerName}"
                    )
                }
            }
            BudleMultiSelectableDropDownMenu(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onValueChange = {
                    if (viewModel.selectedTagNames.contains(it)) {
                        viewModel.selectedTagNames.remove(it)
                    } else viewModel.selectedTagNames.add(it)
                },
                items = viewModel.tagList,
                selectedItems = viewModel.selectedTagNames,
                placeHolder = "Теги заведения",
                startMessage = "Выберите теги заведения",
            )
        }
    }
}