package fit.budle.ui.components.moleculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleBlockWithHeader(
    modifier: Modifier = Modifier,
    headerPadding: Dp = 0.dp,
    headerText: String,
    rightText: String? = null,
    content: @Composable() (() -> Unit)
) {
    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = headerPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = headerText,
                style = MaterialTheme.typography.titleSmall,
                color = mainBlack
            )
            if (rightText != null) {
                Text(
                    text = rightText,
                    style = MaterialTheme.typography.displaySmall,
                    color = textGray
                )
            }
        }
        content()
    }
}