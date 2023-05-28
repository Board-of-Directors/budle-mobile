package fit.budle.ui.components.moleculas.day_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import fit.budle.dto.establishment.WorkingHoursDto

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BudleWorkingDaysPickerList(
    daysCount: Int,
    selectedItems: SnapshotStateMap<Int, WorkingHoursDto>,
) {
    for (day in 0 until daysCount){
        BudleWorkingDaysPicker(
            onValueChange = { selectedItems[day] = it },
            selectedWorkingHoursDto = selectedItems[day]
        )
        Log.d("TAGLIST",selectedItems[day]?.daysOfWork?.size.toString())
    }
}