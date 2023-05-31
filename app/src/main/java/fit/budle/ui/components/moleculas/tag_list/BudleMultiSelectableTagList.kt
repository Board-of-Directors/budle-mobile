package fit.budle.ui.components.moleculas.tag_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.Tag
import fit.budle.ui.components.BudleCircleTag
import fit.budle.ui.components.BudleRectangleTag
import fit.budle.ui.theme.mainBlack

@SuppressLint("MutableCollectionMutableState")
@Composable
fun BudleMultiSelectableTagList(
    modifier: Modifier = Modifier,
    onValueChange: (Set<Tag>) -> Unit,
    tagList: List<Tag>,
    tagType: ActiveTagType = ActiveTagType.RECTANGLE,
    textColor: Color = mainBlack,
    showDate: Boolean = true,
    startValue: SnapshotStateMap<Tag, Boolean> = tagList.map { it to false }.toMutableStateMap(),
    isAlreadyPicked: (String) -> Boolean = { false },
) {

    val selectedItems = remember { startValue }

    val isSelectedItem: (Tag) -> (Boolean) = {
        selectedItems[it] == true
    }
    val onChangeState: (Tag) -> Unit = {
        if (!isAlreadyPicked(it.tagName) || isSelectedItem(it)) {
            selectedItems[it] = !isSelectedItem(it)
        }
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup()
    ) {
        itemsIndexed(tagList) { _, tag ->
            if (tagType == ActiveTagType.RECTANGLE) {
                BudleRectangleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    tag = tag,
                    color = textColor
                )
            } else {
                BudleCircleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    showDate = showDate,
                    tag = tag,
                    color = textColor
                )
            }
        }
    }
    onValueChange(
        selectedItems.filter { it.value }.keys
    )
}