package fit.budle.components.atoms.tags

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fit.budle.models.Tag
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.models.tagList

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
    val textPadding = if (tag.iconId != null) 10.dp else 0.dp

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
            if (tag.iconId != null) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = tag.iconId),
                    contentDescription = "Tag icon",
                    tint = textColor
                )
            }
            Text(
                modifier = Modifier.padding(start = textPadding),
                text = tag.tagName,
                style = MaterialTheme.typography.labelSmall,
                color = textColor
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun PreviewBudleTag() {
    BudleRectangleTag(
        isSelected = { true },
        onChangeState = {},
        tag = tagList[0]
    )
}