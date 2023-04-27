package fit.budle.ui.components.atoms.inputs.photo_inputs

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fit.budle.R
import fit.budle.ui.components.atoms.BudleIconButton

@Composable
fun BudleMultiplePickerState(
    stroke: Stroke,
    selectedImageUris: List<Uri>,
    onClick: () -> Unit,
    onValueChange: (Uri) -> (Unit)
) {

    BudleDisabledPhotoPicker(
        stroke = stroke,
        onClick = onClick
    )

    if (selectedImageUris.isNotEmpty()) {
        LazyRow(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            itemsIndexed(selectedImageUris) { _, uri ->
                Card(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(70.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = uri,
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
                                onClick = { onValueChange(uri) }
                            )
                        }
                    }
                }
            }
        }
    }
}