package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.models.Tag
import fit.budle.ui.theme.textGray

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleMultiColumnTagList(
    selectable: Boolean = true,
    selectedItem: MutableState<String>,
    tags: MutableList<MutableList<Tag>>
){
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(tags) { _, row ->
            selectedItem.value = budleTagList(
                selectable = selectable,
                tagList = row,
                textColor = textGray,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}