package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.BudleDayTag
import fit.budle.components.atoms.BudleTag
import fit.budle.components.data.TagType
import fit.budle.models.RectangleTag
import fit.budle.models.Tag

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun budleTagList(
    initialState: Int = -1,
    tagList: List<Tag>,
    tagType: TagType = TagType.RECTANGLE
): String {

    var selectedItem by remember { mutableStateOf(initialState) }
    val isSelectedItem: (Int) -> Boolean = { selectedItem == it }
    val onChangeState: (Int) -> Unit = {
        selectedItem = if (!isSelectedItem(it)) it else selectedItem
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
    ) {
        itemsIndexed(tagList) { _, tag ->
            if (tagType == TagType.RECTANGLE) {
                BudleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    tag = tag
                )
            } else {
                BudleDayTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    tag = tag
                )
            }
        }
    }
    return if (selectedItem != -1){
        tagList.first { tag -> tag.tagId == selectedItem }.tagName
    } else ""
}