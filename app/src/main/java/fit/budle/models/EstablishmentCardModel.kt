package fit.budle.models

import android.os.Parcelable
import fit.budle.R
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class EstablishmentCardModel(
    val establishmentDescription: EstablishmentDescription,
    val tags: @RawValue MutableList<InfoTag>,
    val infoList: MutableList<Pair<String, Any>>
) : Parcelable

val sampleEstablishmentCardModel = EstablishmentCardModel(
    establishmentDescriptionList[0],
    bookingTagList,
    mutableListOf(
        "Описание" to "Уютный ресторан Мама Дома —\n" +
                "это чудесная локация с камином, глубокими креслами" +
                "и идеальной для общения с близкими атмосферой." +
                "В меню объединились итальянские и русские рецепты," +
                "современные европейские гастрономические тренды и" +
                "экспериментальные сочетания вкусов.",
        "Фотографии" to mutableListOf(
            R.drawable.establishment_photo_1,
            R.drawable.establishment_photo_2,
            R.drawable.establishment_photo_3,
            R.drawable.establishment_photo_4,
            R.drawable.establishment_photo_5,
            R.drawable.establishment_photo_6,
            R.drawable.establishment_photo_1,
            R.drawable.establishment_photo_2,
            R.drawable.establishment_photo_3,
            R.drawable.establishment_photo_4,
        ),
        "Часы работы" to mutableMapOf(
            "Пн-Пт" to "10:00 — 21:00",
            "Cб" to "11:00 — 19:00",
            "Вс" to "Выходной"
        ),
        "Адрес" to mutableMapOf(
            "Метро" to "м. Площадь Ленина",
            "Адрес" to "ул. Советская, д. 50"
        )
    )
)