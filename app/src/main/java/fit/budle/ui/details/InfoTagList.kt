package fit.budle.ui.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.model.tag.standard.Tag

@Composable
fun InfoTagList(
    tags: MutableList<Tag>,
    horizontalPadding: Dp = 20.dp
) {
    LazyRow(
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        itemsIndexed(tags) { _, tag ->
            InfoTag(infoTag = tag)
        }
    }
}