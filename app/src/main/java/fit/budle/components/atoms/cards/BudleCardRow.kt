package fit.budle.components.atoms.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleCardRow(
        modifier: Modifier = Modifier,
        iconButton: Boolean = false,
        onClickIconButton: () -> Unit = {},
        topPadding: Dp = 20.dp,
        iconId: Int? = null,
        header: String,
        headerSize: TextStyle,
        description: String,
        descriptionColor: Color = textGray
) {
    Row(
            modifier = modifier
                    .padding(top = topPadding)
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
                text = header,
                style = headerSize,
                color = mainBlack
        )
        if (!iconButton){
            Text(
                    text = description,
                    style = MaterialTheme.typography.labelSmall,
                    color = descriptionColor
            )
        } else if (iconId != null){
            IconButton(
                    onClick = onClickIconButton
            ) {
                Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = "Down",
                        tint = textGray
                )
            }
        }
    }
}