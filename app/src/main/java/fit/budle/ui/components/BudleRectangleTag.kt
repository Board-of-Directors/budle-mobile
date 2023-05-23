package fit.budle.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fit.budle.dto.tag.active.Tag
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleRectangleTag(
    isSelected: (Tag) -> Boolean,
    onChangeState: (Tag) -> Unit,
    tag: Tag,
    color: Color = mainBlack,
) {

    val buttonColor = ButtonDefaults.buttonColors(
        if (isSelected(tag)) fillPurple else lightBlue
    )
    val textColor = if (isSelected(tag)) mainWhite else color

    Button(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(40.dp)
            .padding(end = 10.dp),
        colors = buttonColor,
        onClick = { onChangeState(tag) },
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tag.tagName,
                style = MaterialTheme.typography.labelSmall,
                color = textColor
            )
        }
    }
}