package fit.budle.components.moleculas

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fit.budle.components.atoms.BudleCircleTag
import fit.budle.components.atoms.tags.BudleRectangleTag
import fit.budle.components.data.TagType
import fit.budle.models.Tag
import fit.budle.ui.theme.mainBlack

@SuppressLint("MutableCollectionMutableState")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun budleMultiSelectableTagList(
    modifier: Modifier = Modifier,
    tagList: List<Tag>,
    tagType: TagType = TagType.RECTANGLE,
    textColor: Color = mainBlack,
    showDate: Boolean = true
): MutableSet<Tag> {

    val selectedItems = remember {
        tagList.map{it to false}.toMutableStateMap()
    }

    val isSelectedItem: (Tag) -> (Boolean) = {
        selectedItems[it] == true
    }
    val onChangeState: (Tag) -> Unit = {
        selectedItems[it] = !isSelectedItem(it)
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
    return selectedItems.keys
}