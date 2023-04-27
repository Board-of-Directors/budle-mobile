package fit.budle.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    isSelected: (Int) -> Boolean,
    onChangeState: (Int) -> Unit,
    textColor: Color = mainBlack,
    tag: Tag
) {
    val tagColor = CardDefaults.cardColors(
        if (isSelected(tag.tagId)) fillPurple else lightBlue
    )
    val color = if (isSelected(tag.tagId)) mainWhite else textColor

    Card(
        modifier = Modifier
            .padding(end = 10.dp)
            .size(45.dp)
            .selectable(
                selected = isSelected(tag.tagId),
                onClick = { onChangeState(tag.tagId) },
            ),
        colors = tagColor,
        shape = CircleShape
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = tag.tagId.toString(),
                style = MaterialTheme.typography.displaySmall,
                color = color
            )
            Text(
                text = tag.tagName,
                style = MaterialTheme.typography.displaySmall,
                color = color
            )
        }
    }
}
