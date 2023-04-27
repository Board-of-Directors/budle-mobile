package fit.budle.ui.components.moleculas.day_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.days
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleFromToTimeInput
import fit.budle.ui.components.atoms.switch.budleSwitchWithDescription
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.tag_list.budleMultiSelectableTagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleWorkingDaysPicker() {

    var enabled by remember { mutableStateOf(true) }

    BudleBlockWithHeader(headerText = "Дни работы") {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            /*
            budleMultiSelectableTagList(
                modifier = Modifier.padding(top = 20.dp),
                showDate = false,
                tagList = days,
                tagType = ActiveTagType.CIRCLE
            )
            */
            BudleFromToTimeInput(
                enabled = enabled,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            )
            enabled = !budleSwitchWithDescription(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                text = "Выходной"
            )
        }
    }
}