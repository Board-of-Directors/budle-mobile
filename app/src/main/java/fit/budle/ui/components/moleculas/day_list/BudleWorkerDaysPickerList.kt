package fit.budle.ui.components.moleculas.day_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import fit.budle.dto.WorkingHour

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleWorkingDaysPickerList(
    items: Int,
    onValueChange: (List<WorkingHour>) -> Unit,
) {
    for (i in 0 until items) {
        BudleWorkingDaysPicker(
            onValueChange = {
                onValueChange(it)
            }
        )
    }
}