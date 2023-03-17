package fit.budle.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import fit.budle.R

data class Establishments(
    val type: String,
    val amount: String,
    val establishmentList: SnapshotStateList<EstablishmentCard>
)

data class EstablishmentCard(
    override val type: String,
    override val name: String,
    override val imgID: Int,
    override val rate: Double,
) : Establishment

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
        4.8
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