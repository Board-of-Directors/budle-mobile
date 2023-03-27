package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleWorkingDaysPickerList(
    items: Int,
) {
    for (i in 0 until items){
        BudleWorkingDaysPicker()
    }
}
