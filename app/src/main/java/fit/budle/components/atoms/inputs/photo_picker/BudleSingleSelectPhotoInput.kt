package fit.budle.components.atoms.inputs.photo_picker

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import fit.budle.components.atoms.inputs.BudleDisabledPhotoPicker
import fit.budle.components.moleculas.BudleBlockWithHeader

@Composable
fun BudleSingleSelectPhotoInput() {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val deleteImageUri = { selectedImageUri = null }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )

    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
    )

    BudleBlockWithHeader(headerText = "Обложка") {
        if (selectedImageUri == null) {
            BudleDisabledPhotoPicker(
                stroke = stroke,
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )
        } else {
            BudleActiveSinglePhotoPicker(
                selectedImageUri = selectedImageUri,
                onValueChanged = deleteImageUri
            )
        }
    }
}