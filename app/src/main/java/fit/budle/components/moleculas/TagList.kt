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
import fit.budle.components.atoms.BudleTag
import fit.budle.models.tagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleTagList() {

    var selectedItem by remember { mutableStateOf(0) }
    val isSelectedItem: (Int) -> Boolean = { selectedItem == it }
    val onChangeState: (Int) -> Unit = {
        selectedItem = if (!isSelectedItem(it)) it else selectedItem
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .selectableGroup()
    ) {
        itemsIndexed(tagList) { _, tag ->
            BudleTag(
                isSelected = isSelectedItem,
                onChangeState = onChangeState,
                tag = tag
            )
        }
    }
}