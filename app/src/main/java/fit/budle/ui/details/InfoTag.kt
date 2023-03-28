package fit.budle.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fit.budle.model.tag.standard.Tag
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.textGray

@Composable
fun InfoTag(
    infoTag: Tag,
    contentColor: Color = textGray
) {
    Button(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(35.dp)
            .padding(end = 10.dp),
        colors = ButtonDefaults.buttonColors(lightBlue),
        onClick = {},
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(
            horizontal = 15.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            infoTag.image?.let {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = it,
                    contentDescription = "Tag icon",
                )
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = infoTag.name,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor
            )
        }
    }
}
