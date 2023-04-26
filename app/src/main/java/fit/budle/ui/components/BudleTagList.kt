package fit.budle.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.Tag

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun budleTagList(
    modifier: Modifier = Modifier,
    initialState: Int = -1,
    tagList: List<Tag>,
    tagType: ActiveTagType = ActiveTagType.RECTANGLE
): Tag {
    var selectedItem by remember { mutableStateOf(initialState) }
    val isSelectedItem: (Int) -> Boolean = { selectedItem == it }
    val onChangeState: (Int) -> Unit = {
        selectedItem = if (!isSelectedItem(it)) it else selectedItem
    }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup()
    ) {
        itemsIndexed(tagList) { _, tag ->
            if (tagType == ActiveTagType.RECTANGLE) {
                BudleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    tag = tag
                )
            } else {
                BudleCircleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    tag = tag
                )
            }
        }
    }
    return if (selectedItem != -1) {
        tagList.first { tag -> tag.tagId == selectedItem }
    } else tagList.first()
}
