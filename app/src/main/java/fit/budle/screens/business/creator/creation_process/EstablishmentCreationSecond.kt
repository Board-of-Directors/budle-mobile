package fit.budle.screens.business.creator.creation_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.moleculas.BudleBlockWithHeader
import fit.budle.components.moleculas.BudleMultiColumnTagList
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.components.moleculas.budleTagList
import fit.budle.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.models.establishmentCreationTags
import fit.budle.models.establishmentCreationType
import fit.budle.models.restaurantsType
import fit.budle.models.startsType
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.textGray

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentCreationSecondScreen(
    navHostController: NavHostController
) {
    val selectedType = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "40%",
            route = NavRoute.EstablishmentCreationThird.route,
            textMessage = "Создание заведения"
        ) {
            Text(text = selectedType.value)
            SecondEstablishmentCreationType(
                selectedType = selectedType
            )
            SecondEstablishmentCreationSubtype(
                type = selectedType
            )
            SecondEstablishmentCreationInfoTags()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SecondEstablishmentCreationType(
    selectedType: MutableState<String>
) {
    BudleBlockWithHeader(headerText = "Тип заведения") {
        BudleMultiColumnTagList(
            selectedItem = selectedType,
            tags = establishmentCreationType
        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SecondEstablishmentCreationSubtype(
    type: MutableState<String>
) {
    val selectedSubtype = remember { mutableStateOf("") }
    when (type.value) {
        "Рестораны" -> {
            BudleBlockWithHeader(headerText = "Вид кухни") {
                BudleMultiColumnTagList(
                    selectedItem = selectedSubtype,
                    tags = restaurantsType
                )
            }
        }
        "Гостиницы" -> {
            BudleBlockWithHeader(headerText = "Количество звезд") {
                BudleMultiColumnTagList(
                    selectedItem = selectedSubtype,
                    tags = startsType
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SecondEstablishmentCreationInfoTags() {
    val selectedInfoTag = remember { mutableStateOf("") }
    BudleBlockWithHeader(headerText = "Тэги заведения") {
        BudleMultiColumnTagList(
            selectable = true,
            selectedItem = selectedInfoTag,
            tags = establishmentCreationTags
        )
    }
}