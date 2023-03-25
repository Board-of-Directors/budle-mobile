package fit.budle.components.atoms.inputs

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fit.budle.R
import fit.budle.components.atoms.BudleIconButton
import fit.budle.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.theme.*

@Composable
fun BudleSingleSelectPhotoInput() {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val deleteImageUri: () -> Unit = { selectedImageUri = null }
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
            DisabledPhotoPicker(
                stroke = stroke,
                singlePhotoPickerLauncher = singlePhotoPickerLauncher
            )
        } else {
            ActivePhotoPicker(
                selectedImageUri = selectedImageUri,
                onValueChanged = deleteImageUri
            )
        }
    }
}

@Composable
fun DisabledPhotoPicker(
    stroke: Stroke,
    singlePhotoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .height(130.dp)
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(
                    color = textGray,
                    style = stroke,
                    cornerRadius = CornerRadius(15.dp.toPx())
                )
            }
            .clickable(
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ),
        colors = CardDefaults.cardColors(lightBlue),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Выберите фотографию",
                    tint = textGray
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Выберите фотографию",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray
                )
            }
        }
    }
}

@Composable
fun ActivePhotoPicker(
    selectedImageUri: Uri?,
    onValueChanged: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(130.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
        ) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = "Выбранное изображение",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
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
                    onClick = onValueChanged
                )
            }
        }
    }
}