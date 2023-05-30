package fit.budle.ui.components.moleculas.day_list.business

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import fit.budle.dto.establishment.RequestWorkingHoursDto

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BudleWorkingDaysPickerList(
    daysCount: Int,
    onValueChange: (Int, RequestWorkingHoursDto) -> Unit,
    selectedItems: SnapshotStateMap<Int, RequestWorkingHoursDto>,
) {
    for (day in 0 until daysCount) {
        BudleWorkingDaysPicker(
            onValueChange = { onValueChange(day, it) },
            selectedWorkingHoursDto = selectedItems[day]
        )
        Log.d("TAGLIST", selectedItems[day]?.days?.size.toString())
    }
}