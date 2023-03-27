package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fit.budle.components.atoms.BudleCircleTag
import fit.budle.components.atoms.tags.BudleRectangleTag
import fit.budle.components.data.TagType
import fit.budle.models.Tag
import fit.budle.ui.theme.mainBlack

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun budleTagList(
    selectable: Boolean = true,
    initialState: Tag = Tag(-1,""),
    tagList: List<Tag>,
    tagType: TagType = TagType.RECTANGLE,
    textColor: Color = mainBlack,
    showDate: Boolean = true,
    modifier: Modifier = Modifier
): Tag {

    var selectedItem by remember { mutableStateOf(initialState) }
    val isSelectedItem: (Tag) -> Boolean = { if (selectable) selectedItem == it else false }
    val onChangeState: (Tag) -> Unit = {
        selectedItem = if (!isSelectedItem(it) && selectable) it else selectedItem
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup()
    ) {
        itemsIndexed(tagList) { _, tag ->
            if (tagType == TagType.RECTANGLE) {
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
    return selectedItem
}