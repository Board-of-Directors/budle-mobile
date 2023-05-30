package fit.budle.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import fit.budle.dto.tag.standard.IconTag
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.textGray

@Composable
fun BudleInfoTag(
    infoIconTag: IconTag,
    contentColor: Color = textGray,
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
            infoIconTag.image?.let {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = it,
                    contentDescription = "Tag icon",
                )
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = infoIconTag.name,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor
            )
        }
    }
}
