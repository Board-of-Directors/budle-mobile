package fit.budle.components.moleculas

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.mainBlack

@Composable
fun BudleBlockWithHeader(
    modifier: Modifier = Modifier,
    headerText: String,
    content: @Composable() (() -> Unit)
) {
    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.titleSmall,
            color = mainBlack
        )
        content()
    }
}