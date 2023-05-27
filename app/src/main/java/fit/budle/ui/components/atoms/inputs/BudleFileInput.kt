package fit.budle.ui.components.atoms.inputs

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.ui.components.atoms.BudleIconButton
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray
import fit.budle.util.getFileName

@Composable
fun BudleFileInput(
    onFileSelect: (Uri?) -> Unit,
    headerText: String,
    initialState: Uri?,
) {

    val resolver = LocalContext.current.contentResolver

    var selectedFileUri by remember { mutableStateOf(initialState) }
    val deleteImageUri = { selectedFileUri = null }

    val contentIcon = if (selectedFileUri == null) R.drawable.file else R.drawable.check
    val contentColor = if (selectedFileUri == null) textGray else fillPurple
    val inputText = if (selectedFileUri == null) "Выберите файл" else {
        selectedFileUri!!.getFileName(resolver)!!
    }

    val filePicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { file -> if (file != null) selectedFileUri = file }

    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
    )

    BudleBlockWithHeader(headerText = headerText) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        ) {
            BudleInputCard(
                iconId = contentIcon,
                contentColor = contentColor,
                inputText = inputText,
                stroke = stroke,
                height = 90.dp,
                onClick = {
                    filePicker.launch("image/*")
                }
            )
            if (selectedFileUri != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    BudleIconButton(
                        modifier = Modifier.size(26.dp),
                        iconDescription = "Close",
                        iconId = R.drawable.x,
                        onClick = deleteImageUri
                    )
                }
            }
        }
        onFileSelect(selectedFileUri)
    }
}