package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import fit.budle.components.atoms.CircleTag
import fit.budle.components.atoms.Tag
import fit.budle.model.Tag
import fit.budle.model.TagType

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun tagList(
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
                Tag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    tag = tag
                )
            } else {
                CircleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    tag = tag
                )
            }
        }
    }
    return if (selectedItem != -1) {
        tagList.first { tag -> tag.tagId == selectedItem }.tagName
    } else ""
}
