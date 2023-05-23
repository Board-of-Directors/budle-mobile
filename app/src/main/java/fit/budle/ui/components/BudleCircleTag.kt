package fit.budle.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fit.budle.dto.tag.active.Tag
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleCircleTag(
    isSelected: (Tag) -> Boolean,
    onChangeState: (Tag) -> Unit,
    showDate: Boolean = true,
    tag: Tag,
    color: Color = mainBlack
) {
    val tagColor = CardDefaults.cardColors(
        if (isSelected(tag)) fillPurple else lightBlue
    )
    val textColor = if (isSelected(tag)) mainWhite else color

    Card(
        modifier = Modifier
            .padding(end = 10.dp)
            .size(45.dp)
            .selectable(
                selected = isSelected(tag),
                onClick = { onChangeState(tag) },
            ),
        colors = tagColor,
        shape = CircleShape
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showDate){
                Text(
                    text = tag.tagId.toString(),
                    style = MaterialTheme.typography.displaySmall,
                    color = textColor
                )
            }
            Text(
                text = tag.tagName,
                style = MaterialTheme.typography.displaySmall,
                color = textColor
            )
        }
    }
}