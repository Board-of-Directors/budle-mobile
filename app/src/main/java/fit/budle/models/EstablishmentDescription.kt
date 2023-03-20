package fit.budle.models

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import fit.budle.R
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class EstablishmentDescription(
    val subway: String,
    val place: String,
    val subtype: String,
    override val type: String,
    override val name: String,
    override val imgID: Int,
    override val rate: Double,
    val isFavorite: @RawValue MutableState<Boolean> = mutableStateOf(false)
) : Establishment, Parcelable

val establishmentDescriptionList = mutableStateListOf(
    EstablishmentDescription(
        "Академгородок",
        "ул. Пирогова, д. 2",
        "Грузинская",
        "Ресторан",
        "Аджикинежаль",
        R.drawable.institutions_restaurants_3,
        4.8,
    ),
    EstablishmentDescription(
        "Площадь Ленина",
        "ул. Советская, д. 50",
        "Европейская",
        "Гостиница",
        "Горячий цех",
        R.drawable.institutions_restaurants_1,
        4.7,
    ),
    EstablishmentDescription(
        "Маршала Покрышкина",
        "ул. Ольги Жилиной, д. 12",
        "Грузинская",
        "Ресторан",
        "Мама, я дома!",
        R.drawable.institutions_restaurants_2,
        4.8,
    ),
)