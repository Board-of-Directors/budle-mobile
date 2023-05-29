package fit.budle.ui.components.moleculas

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.dto.tag.active.Tag
import fit.budle.ui.components.moleculas.tag_list.BudleTagList

@Composable
fun BudleBookingTimeBlock(
    initialListState: List<Tag>,
    onValueChange: (Tag) -> Unit,
) {
    BudleBlockWithHeader(headerText = "Время") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BudleTagList(
                initialState = initialListState[0],
                tagList = initialListState,
                onValueChange = onValueChange
            )
        }
    }
}