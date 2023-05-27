package fit.budle.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.ui.components.atoms.inputs.dropdown.BudleDropDownMenu
import fit.budle.ui.components.moleculas.tag_list.BudleTagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
@Preview
fun BudleFilterPopup(

) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Column() {
            BudleDropDownMenu(
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = {},
                selectedItem = null,
                items = ArrayList(),
                placeHolder = "Тип заведения",
                startMessage = "Выберите тип заведения",
                isError = false
            )
            Text(text = "Интерактивная карта")
            BudleTagList(
                onValueChange = {},
                initialState = createArrayList()[0],
                tagList = createArrayList()
            )
            Text(text = "Безналичный расчет")
            BudleTagList(
                onValueChange = {},
                initialState = createArrayList()[0],
                tagList = createArrayList()
            )
        }
    }

}

fun createArrayList(): List<RectangleActiveTag> {
    val arrayList = ArrayList<RectangleActiveTag>()
    arrayList.add(RectangleActiveTag("Неважно", 0))
    arrayList.add(RectangleActiveTag("Есть", 1))
    arrayList.add(RectangleActiveTag("Отсутствует", 2))
    return arrayList
}