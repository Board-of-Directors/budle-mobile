package fit.budle.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.Tag
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun budleTagList(
    modifier: Modifier = Modifier,
    selectable: Boolean = true,
    initialState: Int = -1,
    tagList: List<Tag>,
    tagType: ActiveTagType = ActiveTagType.RECTANGLE,
    textColor: Color = mainBlack
): Int {

    var selectedItem by remember { mutableStateOf(initialState) }
    val isSelectedItem: (Int) -> Boolean = { if (selectable) selectedItem == it else false}
    val onChangeState: (Int) -> Unit = {
        selectedItem = if (!isSelectedItem(it) && selectable) it else selectedItem
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
                    textColor = textColor,
                    tag = tag
                )
            } else {
                BudleCircleTag(
                    isSelected = isSelectedItem,
                    onChangeState = onChangeState,
                    textColor = textColor,
                    tag = tag
                )
            }
        }
    }
    return selectedItem
}
