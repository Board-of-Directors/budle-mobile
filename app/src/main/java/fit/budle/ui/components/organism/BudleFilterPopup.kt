package fit.budle.ui.components.organism

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import fit.budle.R
import fit.budle.dto.FilterData
import fit.budle.event.customer.MainEvent
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.inputs.dropdown.BudleDropDownMenu
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.tag_list.BudleTagList
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.viewmodel.customer.MainViewModel

@Composable
fun BudleFilterPopup(
    navHostController: NavController,
    viewModel: MainViewModel,
    onClose: () -> Unit,
) {

    val buttonNumber = viewModel.filteredEstablishmentList.size

    val establishmentName = when (buttonNumber % 10) {
        1 -> "заведение"
        2, 3, 4 -> "заведения"
        5, 6, 7, 8, 9, 0 -> "заведений"
        else -> ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(20f)
            .background(mainBlack.copy(0.6f)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(mainWhite)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                BudleDropDownMenu(
                    onValueChange = {
                        viewModel.selectedEstType = it
                    },
                    selectedItem = viewModel.selectedEstType,
                    startMessage = "Выберите тип заведения",
                    placeHolder = "Тип заведения",
                    items = FilterData.establishmentTypeList
                )
                BudleBlockWithHeader(headerText = "Часы работы") {
                    BudleTagList(
                        modifier = Modifier.padding(top = 10.dp),
                        onValueChange = {
                            viewModel.selectedWorkingHours = it.tagName
                        },
                        tagList = FilterData.workingHourList
                    )
                }
                BudleBlockWithHeader(headerText = "Интерактивная карта") {
                    BudleTagList(
                        modifier = Modifier.padding(top = 10.dp),
                        onValueChange = {
                            viewModel.selectedMapType = it.tagName
                        },
                        tagList = FilterData.characteristicData
                    )
                }
                BudleBlockWithHeader(headerText = "Безналичный расчёт") {
                    BudleTagList(
                        modifier = Modifier.padding(top = 10.dp),
                        onValueChange = {
                            viewModel.selectedPaymentType = it.tagName
                        },
                        tagList = FilterData.characteristicData
                    )
                }
                BudleButton(
                    onClick = {
                        navHostController.navigate("searchScreen")
                    },
                    horizontalPadding = 0.dp,
                    buttonText = "Смотреть $buttonNumber $establishmentName",
                    disabledButtonColor = fillPurple,
                    disabledTextColor = mainWhite
                )
            }
            viewModel.onEvent(MainEvent.GetFilteredEstablishments)
        }
        BudleButton(
            onClick = onClose,
            buttonText = "Фильтры",
            iconId = R.drawable.filter,
            topPadding = 10.dp,
            bottomPadding = 20.dp,
            horizontalPadding = 20.dp,
            disabledButtonColor = mainWhite,
            disabledTextColor = fillPurple
        )
    }
}