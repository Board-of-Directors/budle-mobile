package fit.budle.dto.tag

import androidx.compose.runtime.remember
import fit.budle.R
import fit.budle.dto.tag.active.RectangleActiveTag

val subwayStations = mutableListOf(
    "Отсутствует",
    "м. Красный проспект",
    "м. Октябрьская",
    "м. Сибирская",
    "м. Маршала Покрышкина"
)

val establishmentCreationCategoryList = mutableListOf(
    "Рестораны", "Гостиницы", "Банки", "Медицина",
    "Государство", "Авто", "Развлечения", "Стройка и ремонт"
)

val establishmentCreationTags = mutableListOf(
    RectangleActiveTag(
        "WI-FI", 1,
        R.drawable.wifi
    ),
    RectangleActiveTag(
        "Розетки", 2,
        R.drawable.zap
    ),
    RectangleActiveTag(
        "Телевизоры", 3,
        R.drawable.tv
    ),
    RectangleActiveTag(
        "Тихое место", 4,
        R.drawable.headphones
    ),
    RectangleActiveTag(
        "Открытая кухня", 5,
        R.drawable.eye
    ),
    RectangleActiveTag(
        "Танцпол", 6,
        R.drawable.music
    )
)