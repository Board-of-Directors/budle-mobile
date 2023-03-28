package fit.budle.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.mainBlack

@Composable
fun BlockWithHeader(
    modifier: Modifier = Modifier,
    headerPadding: Dp = 0.dp,
    headerText: String,
    content: @Composable() (() -> Unit)
) {
    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(start = headerPadding),
            text = headerText,
            style = MaterialTheme.typography.titleSmall,
            color = mainBlack
        )
        content()
    }
}