package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import fit.budle.event.business.EstCreationEvent
import fit.budle.ui.components.atoms.inputs.BudleFileInput
import fit.budle.ui.components.atoms.switch.BudleSwitch
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.mainBlack
import fit.budle.util.convertToFile
import fit.budle.viewmodel.business.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationMapScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            onClick = {
                viewModel.onEvent(EstCreationEvent.CreateMap)
                navHostController.navigate("fifthStep")
            },
            buttonText = "Следующий шаг",
            textMessage = "Создание заведения",
            progress = "20%"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Карта заведения",
                    style = MaterialTheme.typography.bodyMedium,
                    color = mainBlack
                )
                BudleSwitch(onSwitch = {
                    viewModel.hasMap = !viewModel.hasMap
                })
            }
            if (viewModel.hasMap) {
                BudleFileInput(
                    onFileSelect = {
                        viewModel.selectedMapUri = it
                        if (it != null) {
                            viewModel.selectedMapFile = it.convertToFile(context)
                        }
                    },
                    headerText = "Выберите карту заведения",
                    initialState = viewModel.selectedMapUri
                )
            }
        }
    }
}
