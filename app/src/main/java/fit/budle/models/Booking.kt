package fit.budle.models

import fit.budle.R

data class Booking(
    val establishmentDescription: EstablishmentDescription,
    val tags: MutableList<InfoTag>,
    val infoList: MutableList<Pair<String, Any>>
)

data class InfoTag(
    val name: String,
    val iconId: Int
)

val bookingTagList = mutableListOf(
    InfoTag(
        "С зарядкой",
        R.drawable.zap
    ),
    InfoTag(
        "Телевизор",
        R.drawable.tv
    )
)

val bookingList = mutableListOf(
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
            "Статус" to BookingStatus.WAIT,
            "Время" to "21:00, 23 ноября",
            "Кол-во гостей" to 3,
            "Бронь на имя" to "Иван"
        )
    )
)

enum class BookingStatus(val value: Int) {

    WAIT(1),
    CONFIRM(2),
    REJECT(3);

    companion object {
        fun create(x: Int): String {
            return when (x) {
                1 -> "Бронь в ожидании"
                2 -> "Бронь подтверждена"
                3 -> "Бронь отклонена"
                else -> throw IllegalStateException()
            }
        }
    }
}