package fit.budle.components.atoms

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fit.budle.model.Tag
import fit.budle.models.InfoTag
import fit.budle.models.bookingTagList
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleInfoTag(
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
