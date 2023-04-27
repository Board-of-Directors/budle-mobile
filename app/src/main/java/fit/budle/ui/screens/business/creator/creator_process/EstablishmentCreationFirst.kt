package fit.budle.ui.screens.business.creator.creator_process

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fit.budle.dto.establishment.ImageDTO
import fit.budle.dto.events.EstCreationEvent
import fit.budle.ui.components.atoms.inputs.photo_inputs.budleSingleSelectPhotoInput
import fit.budle.ui.components.atoms.inputs.text_inputs.budleSingleLineInput
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.*
import fit.budle.viewmodel.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationFirstScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel
) {

    var selectedImage: Uri? by remember { mutableStateOf(null) }
    var selectedName by remember { mutableStateOf("") }

    val source = if (selectedImage != null) {
        ImageDecoder.createSource(LocalContext.current.contentResolver, selectedImage!!)
    } else null

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            onClick = {
                val bitmap = ImageDecoder.decodeBitmap(source!!)
                viewModel.onEvent(EstCreationEvent.FirstStep(bitmap, selectedName))
                navHostController.navigate("secondStep")
            },
            buttonText = "Следующий шаг",
            textMessage = "Создание заведения",
            progress = "20%"
        ) {
            selectedImage = budleSingleSelectPhotoInput()
            selectedName = budleSingleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                placeHolderColor = mainBlack,
                placeHolder = "Название",
                startMessage = "",
                textFieldMessage = "Введите название"
            )
        }
    }
}