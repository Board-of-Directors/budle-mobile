package fit.budle.models


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import fit.budle.R

data class Booking(
    val establishmentDescription: EstablishmentDescription,
    val tags: MutableList<Tag>,
    val infoList: MutableList<Pair<String, Any>>,
    val isRejected: MutableState<Boolean> = mutableStateOf(false)
)

data class InfoTag(
    override val tagName: String,
    override val tagId: Int,
    override val iconId: Int?
) : Tag

val bookingTagList = mutableListOf<Tag>(
    InfoTag(
        "С зарядкой",
        1,
        R.drawable.zap
    ),
    InfoTag(
        "Телевизор",
        2,
        R.drawable.tv
    ),
    InfoTag(
        "Около окна",
        3,
        R.drawable.sun
    )
)

val bookingList = mutableStateListOf(
    Booking(
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
    }
}