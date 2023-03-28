package fit.budle.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import fit.budle.model.tag.active.ActiveTagType
import fit.budle.model.tag.active.Tag

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun tagList(
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
        tagList.first { tag -> tag.tagId == selectedItem }
    } else tagList.first()
}
