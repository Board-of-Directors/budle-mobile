package nsu.app.budle.components.atoms

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import nsu.app.budle.models.Tag
import nsu.app.budle.ui.theme.fillPurple
import nsu.app.budle.ui.theme.lightBlue
import nsu.app.budle.ui.theme.mainBlack
import nsu.app.budle.ui.theme.mainWhite

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleTag(
    isSelected: (Int) -> Boolean,
    onChangeState: (Int) -> Unit,
    tag: Tag
) {

    val buttonColor = ButtonDefaults.buttonColors(
        if (isSelected(tag.tagId)) fillPurple else lightBlue
    )
    val textColor = if (isSelected(tag.tagId)) mainWhite else mainBlack

    Button(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(50.dp)
            .padding(end = 10.dp),
        colors = buttonColor,
        onClick = { onChangeState(tag.tagId) },
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = tag.name,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}