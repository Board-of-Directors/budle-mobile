package fit.budle.models

import android.os.Parcelable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import fit.budle.R
import kotlinx.parcelize.Parcelize

data class Establishments(
    val type: String,
    val amount: String,
    val establishmentList: SnapshotStateList<EstablishmentCard>
)

@Parcelize
data class EstablishmentCard(
    override val type: String,
    override val name: String,
    override val imgID: Int,
    override val rate: Double,
    val establishmentCardModel: EstablishmentCardModel
    = sampleEstablishmentCardModel
) : Establishment, Parcelable

val restaurants = mutableStateListOf(
    EstablishmentCard(
        "Ресторан",
        "Горячий цех",
        R.drawable.institutions_restaurants_1,
        4.7
    ),
    EstablishmentCard(
        "Ресторан",
        "Мама, я дома!",
        R.drawable.institutions_restaurants_2,
        4.8,
        EstablishmentCardModel(
            establishmentDescriptionList[1],
            bookingTagList,
            mutableListOf(
                "Описание" to "Любимые блюда всегда с вами!\n" +
                        "При самовывозе скидка 10% на все блюда в заказе.\n" +
                        "\n" + "Заказывайте хинкали, хачапури, шашлыки и наслаждайтесь" +
                        "вместе с близкими вам людьми!",
                "Фотографии" to mutableListOf(
                    R.drawable.second_establishment_1,
                    R.drawable.second_establishment_2,
                    R.drawable.second_establishment_2,
                    R.drawable.second_establishment_2
                ),
                "Часы работы" to mutableMapOf(
                    "Пн" to "9:00 — 20:00",
                    "Вт-Пт" to "10:00 — 21:00",
                    "Cб-Вс" to "Выходной"
                ),
                "Адрес" to mutableMapOf(
                    "Адрес" to "ул. Некрасова, д. 11/2"
                )
            )
        )
    ),
    EstablishmentCard(
        "Ресторан",
        "Аджикинежаль",
        R.drawable.institutions_restaurants_3,
        4.8
    ),
    EstablishmentCard(
        "Ресторан",
        "Дядя Дёнер",
        R.drawable.institutions_restaurants_2,
        3.7
    ),
)

val hotels = mutableStateListOf(
    EstablishmentCard(
        "Гостиница",
        "Mariott",
        R.drawable.institutions_hotels_1,
        3.2
    ),
    EstablishmentCard(
        "Гостиница",
        "River Plaza",
        R.drawable.institutions_hotels_2,
        4.7
    ),
    EstablishmentCard(
        "Гостиница",
        "Mirotel",
        R.drawable.institutions_hotels_3,
        4.8
    ),
    EstablishmentCard(
        "Гостиница",
        "Double Tree",
        R.drawable.institutions_hotels_2,
        3.3
    ),
)

val establishments = mutableStateListOf(
    Establishments(
        "Рестораны",
        "Все 182",
        restaurants
    ),
    Establishments(
        "Гостиницы",
        "Все 381",
        hotels
    ),
)