package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.budleSwitchWithDescription
import fit.budle.components.atoms.inputs.text_inputs.BudleFromToTimeInput
import fit.budle.components.data.TagType
import fit.budle.models.days

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleWorkingDaysPicker() {

    var enabled by remember { mutableStateOf(true) }

    BudleBlockWithHeader(headerText = "Дни работы") {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            budleMultiSelectableTagList(
                modifier = Modifier.padding(top = 20.dp),
                showDate = false,
                tagList = days,
                tagType = TagType.CIRCLE
            )
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