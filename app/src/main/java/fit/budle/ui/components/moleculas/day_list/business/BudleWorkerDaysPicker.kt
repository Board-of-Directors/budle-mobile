package fit.budle.ui.components.moleculas.day_list.business

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.dto.establishment.WorkingHoursDto
import fit.budle.dto.tag.active.ActiveCircleTag
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.Tag
import fit.budle.dto.tag.active.days
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleFromToTimeInput
import fit.budle.ui.components.atoms.switch.BudleSwitchWithDescription
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.tag_list.BudleMultiSelectableTagList

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BudleWorkingDaysPicker(
    selectedWorkingHoursDto: WorkingHoursDto?,
    onValueChange: (WorkingHoursDto) -> Unit,
) {

    var enabled by remember { mutableStateOf(true) }

    val daysTags = days as List<Tag>

    var fromToTime by remember {
        mutableStateOf(
            Pair(
                selectedWorkingHoursDto?.fromTime ?: "",
                selectedWorkingHoursDto?.toTime ?: ""
            )
        )
    }

    val selectedTagList = remember {
        if (selectedWorkingHoursDto != null) {
            Log.d("ISNOTNULL", "IT")
            convertStringListToTagList(selectedWorkingHoursDto.daysOfWork)
                .toMutableStateList()
        } else {
            Log.d("ISNULL", "IT")
            mutableStateListOf()
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
                    onValueChange(
                        WorkingHoursDto(fromTime = fromToTime.first,
                            toTime = fromToTime.second,
                            daysOfWork = selectedTagList.map { tag -> tag.tagName })
                    )
                },
                showDate = false,
                tagList = daysTags,
                tagType = ActiveTagType.CIRCLE,
                startValue = days.map {
                    if (selectedWorkingHoursDto?.daysOfWork?.contains(it.tagName) == true) {
                        it to true
                    } else {
                        it to false
                    }
                }.toMutableStateMap()
            )
            BudleFromToTimeInput(
                enabled = enabled,
                fromInitialState = fromToTime.first,
                toInitialState = fromToTime.second,
                onValueChange = { fromToTime = it },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            )
            BudleSwitchWithDescription(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                onSwitch = { enabled = it },
                text = "Выходной"
            )
            Log.d("ENABLED", enabled.toString())
        }
        if (fromToTime != Pair("", "")) {
            onValueChange(WorkingHoursDto(
                fromTime = fromToTime.first,
                toTime = fromToTime.second,
                daysOfWork = selectedTagList.map { tag -> tag.tagName }
            ))
        }
    }
}

fun convertStringListToTagList(days: List<String>): List<Tag> {
    val returnList = mutableListOf<Tag>()
    for ((index, day) in days.withIndex()) {
        returnList.add(
            ActiveCircleTag(
                tagId = index,
                tagName = day
            )
        )
    }
    return returnList
}