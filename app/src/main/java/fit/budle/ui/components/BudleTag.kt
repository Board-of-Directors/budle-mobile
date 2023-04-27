package fit.budle.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun BudleTag(
    isSelected: (Int) -> Boolean,
    onChangeState: (Int) -> Unit,
    textColor: Color = mainBlack,
    tag: Tag
) {
    val buttonColor = ButtonDefaults.buttonColors(
        if (isSelected(tag.tagId)) fillPurple else lightBlue
    )
    val color = if (isSelected(tag.tagId)) mainWhite else textColor

    Button(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(40.dp)
            .padding(end = 10.dp),
        colors = buttonColor,
        onClick = { onChangeState(tag.tagId) },
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = tag.tagName,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}
