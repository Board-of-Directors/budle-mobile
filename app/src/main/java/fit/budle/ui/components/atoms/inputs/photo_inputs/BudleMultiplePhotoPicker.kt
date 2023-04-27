package fit.budle.ui.components.atoms.inputs.photo_inputs

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import fit.budle.ui.components.moleculas.BudleBlockWithHeader

@Composable
fun BudleMultiplePhotoInput() {

    var selectedImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    val changeSelectedImageUris: (Uri) -> Unit = {
        selectedImageUris = selectedImageUris.filter { uri ->
            uri != it
        }
    }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            val resultList = mutableListOf<Uri>()
            for (uri in uris) {
                if (!selectedImageUris.contains(uri)
                    && selectedImageUris.size + resultList.size + 1 <= 10
                ) {
                    resultList.add(uri)
                }
            }
            resultList.addAll(selectedImageUris)
            selectedImageUris = resultList
        }
    )

    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
    )

    BudleBlockWithHeader(
        headerText = "Фотографии",
        rightText = "${selectedImageUris.size} / 10 фото"
    ) {
        BudleMultiplePickerState(
            stroke = stroke,
            onClick = {
                multiplePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            selectedImageUris = selectedImageUris,
            onValueChange = changeSelectedImageUris
        )
    }
}