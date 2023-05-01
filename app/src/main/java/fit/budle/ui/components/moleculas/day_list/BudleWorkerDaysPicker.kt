package fit.budle.ui.components.moleculas.day_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.dto.WorkingHour
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.Tag
import fit.budle.dto.tag.active.days
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleFromToTimeInput
import fit.budle.ui.components.atoms.switch.budleSwitchWithDescription
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.tag_list.BudleMultiSelectableTagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleWorkingDaysPicker(
    onValueChange: (List<WorkingHour>) -> Unit,
) {

    var enabled by remember { mutableStateOf(true) }
    var fromToTime by remember { mutableStateOf(Pair("", "")) }
    val selectedTagList = remember { mutableStateListOf<Tag>() }
    val workingHourList = remember { mutableStateListOf<WorkingHour>() }

    Log.d("FROM", fromToTime.first)
    Log.d("TO", fromToTime.second)

    val onWorkingHourChange: () -> Unit = {
        workingHourList.clear()
        for (tag in selectedTagList) {
            workingHourList.add(
                WorkingHour(
                    "12:00",
                    "23:00",
                    tag.tagName
                )
            )
        }
    }

    BudleBlockWithHeader(headerText = "Дни работы") {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            BudleMultiSelectableTagList(
                modifier = Modifier.padding(top = 20.dp),
                onValueChange = {
                    selectedTagList.clear()
                    selectedTagList.addAll(it)
                },
                showDate = false,
                tagList = days as List<Tag>,
                tagType = ActiveTagType.CIRCLE
            )
            BudleFromToTimeInput(
                enabled = enabled,
                onValueChange = { fromToTime = it },
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
        onWorkingHourChange()
        onValueChange(workingHourList)
    }
}