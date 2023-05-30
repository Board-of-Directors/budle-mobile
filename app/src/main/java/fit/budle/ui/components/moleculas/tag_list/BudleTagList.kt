package fit.budle.ui.components.moleculas.tag_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.dto.tag.active.Tag
import fit.budle.ui.components.BudleCircleTag
import fit.budle.ui.components.BudleRectangleTag
import fit.budle.ui.theme.mainBlack

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleTagList(
    modifier: Modifier = Modifier,
    selectable: Boolean = true,
    onValueChange: (Tag) -> Unit,
    initialState: Tag = RectangleActiveTag("", -1),
    tagList: List<Tag>,
    tagType: ActiveTagType = ActiveTagType.RECTANGLE,
    textColor: Color = mainBlack,
) {

    var selectedItem by remember { mutableStateOf(initialState) }
    val isSelectedItem: (Tag) -> Boolean =
        { if (selectable) selectedItem.tagId == it.tagId else false }
    val onChangeState: (Tag) -> Unit = {
        selectedItem = if (!isSelectedItem(it) && selectable) it else selectedItem
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
                    color = textColor,
                    tag = tag
                )
            } else {
                BudleCircleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    color = textColor,
                    tag = tag
                )
            }
        }
        onValueChange(selectedItem)
    }
}
