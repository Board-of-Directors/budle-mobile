package fit.budle.ui.screens.business.creator.creator_process

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import fit.budle.event.business.EstCreationEvent
import fit.budle.ui.components.atoms.inputs.photo_inputs.BudleSingleSelectPhotoInput
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.viewmodel.business.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationMapScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    val source = if (viewModel.selectedMapUri != null) {
        ImageDecoder.createSource(
            LocalContext.current.contentResolver,
            viewModel.selectedMapUri as Uri
        )
    } else null

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            onClick = {
                viewModel.selectedMapBitmap = ImageDecoder.decodeBitmap(source!!)
                viewModel.onEvent(EstCreationEvent.FirstStep)
                navHostController.navigate("fifthStep")
            },
            buttonText = "Следующий шаг",
            textMessage = "Создание заведения",
            progress = "20%"
        ) {
            BudleSingleSelectPhotoInput(
                onValueChange = {
                    viewModel.selectedMapUri = it
                },
                initialState = viewModel.selectedMapUri,
                isError = false,
                headerText = "Загрузите карту заведения"
            )
        }
    }
}