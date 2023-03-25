package fit.budle.components.atoms.inputs.photo_picker

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fit.budle.R
import fit.budle.components.atoms.BudleIconButton

@Composable
fun BudleActiveSinglePhotoPicker(
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