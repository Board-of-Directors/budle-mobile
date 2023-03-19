package fit.budle.models

import androidx.compose.runtime.mutableStateListOf
import fit.budle.R

data class EstablishmentDescription(
    val subway: String,
    val place: String,
    val subtype: String,
    override val type: String,
    override val name: String,
    override val imgID: Int,
    override val rate: Double,
    val isFavorite: Boolean
) : Establishment

val establishmentDescriptionList = mutableStateListOf(
    EstablishmentDescription(
        "Академгородок",
        "ул. Пирогова, д. 2",
        "Грузинская",
        "Ресторан",
        "Аджикинежаль",
        R.drawable.institutions_restaurants_3,
        4.8,
        true
    ),
    EstablishmentDescription(
        "Площадь Ленина",
        "ул. Советская, д. 50",
        "Европейская",
        "Гостиница",
        "Горячий цех",
        R.drawable.institutions_restaurants_1,
        4.7,
        true
    ),
    EstablishmentDescription(
        "Маршала Покрышкина",
        "ул. Ольги Жилиной, д. 12",
        "Грузинская",
        "Ресторан",
        "Мама, я дома!",
        R.drawable.institutions_restaurants_2,
        4.8,
        true
    ),
)