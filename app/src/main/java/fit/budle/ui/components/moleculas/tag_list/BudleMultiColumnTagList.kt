package fit.budle.ui.components.moleculas.tag_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.ui.components.budleTagList
import fit.budle.ui.theme.textGray

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun budleMultiColumnTagList(
    selectable: Boolean = true,
    selectedItem: MutableState<Int>,
    tags: MutableList<MutableList<RectangleActiveTag>>
) : {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (row in tags) {
            selectedItem.value = budleTagList(
                selectable = selectable,
                tagList = row,
                textColor = textGray,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
    return selectedItem.value
}