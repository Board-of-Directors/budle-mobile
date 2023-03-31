package fit.budle.models


import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import fit.budle.R
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundSuccess
import fit.budle.ui.theme.textGray
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Booking(
    val id: Int,
    val tableNumber: Int? = null,
    val establishmentDescription: EstablishmentDescription,
    val tags: MutableList<Tag>,
    val infoList: MutableList<Pair<String, Any>>,
    val isRejected: @RawValue MutableState<Boolean> = mutableStateOf(false),
) : Parcelable

val bookingTagList = mutableListOf (
    Tag(
        1,"С зарядкой",
        R.drawable.zap
    ),
    Tag(
        2,"Телевизор",
        R.drawable.tv
    ),
    Tag(
        3,"Около окна",
        R.drawable.sun
    )
)

val bookingList = mutableStateListOf(
    Booking(
            1,
            16,
        establishmentDescriptionList[0],
        bookingTagList,
        mutableListOf(
            "Статус" to BookingStatus.CONFIRM,
            "Время" to "19:00, 21 ноября",
            "Кол-во гостей" to 2,
            "Бронь на имя" to "Артём"
        )
    ),
    Booking(
            2,
            15,
        establishmentDescriptionList[1],
        bookingTagList,
        mutableListOf(
            "Статус" to BookingStatus.REJECT,
            "Время" to "21:00, 23 ноября",
            "Кол-во гостей" to 3,
            "Бронь на имя" to "Иван"
        )
    )
)

enum class BookingStatus(val value: Int, val message: String) {

    WAIT(0, "Бронь в ожидании"),
    CONFIRM(1, "Бронь подтверждена"),
    REJECT(2, "Бронь отклонена");

    companion object {
        fun create(x:Int) : BookingStatus{
            return when(x){
                0 -> WAIT
                1 -> CONFIRM
                2 -> REJECT
                else -> throw IllegalStateException()
            }
        }
        fun createColor(x:Int) : Color {
            return when(x){
                0 -> textGray
                1 -> backgroundSuccess
                2 -> backgroundError
                else -> throw IllegalStateException()
            }
        }
    }
}