package fit.budle.ui.components.atoms.inputs.photo_inputs

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import fit.budle.ui.components.moleculas.BudleBlockWithHeader

@Composable
fun BudleSingleSelectPhotoInput(
    onValueChange: (Uri?) -> Unit,
    initialState: Uri?,
    isError: Boolean,
    headerText: String = "Обложка",
) {

    var selectedImageUri by remember { mutableStateOf(initialState) }
    val deleteImageUri = { selectedImageUri = null }
    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri }
        )

    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
    )

    BudleBlockWithHeader(headerText = headerText) {
        if (selectedImageUri == null) {
            BudleDisabledPhotoPicker(
                stroke = stroke,
                isError = isError,
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
        onValueChange(selectedImageUri)
    }
}