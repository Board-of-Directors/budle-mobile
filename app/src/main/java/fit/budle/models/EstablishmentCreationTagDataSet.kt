package fit.budle.models

import fit.budle.R

val restaurantsType = mutableListOf(
    mutableListOf(
        Tag(
            1,
            "Грузинская"
        ),
        Tag(
            2,
            "Европейская"
        )
    ),
    mutableListOf(
        Tag(
            3,
            "Азиатская"
        ),
        Tag(
            4,
            "Русская"
        ),
        Tag(
            6,
            "Вьетнамская"
        )
    )
)

val startsType = mutableListOf(
    mutableListOf(
        Tag(
            1, "1 звезда"
        ),
        Tag(
            2, "2 звезды"
        )
    ),
    mutableListOf(
        Tag(
            3, "3 звезды"
        ),
        Tag(
            4, "4 звезды"
        ),
        Tag(
            5, "5 звезд"
        )
    )
)

val establishmentCreationType = mutableListOf(
    mutableListOf(
        Tag(
            1, "Рестораны"
        ),
        Tag(
            2, "Гостиницы"
        ),
        Tag(
            3, "Банки"
        )
    ),
    mutableListOf(
        Tag(
            4, "Медицина"
        ),
        Tag(
            5, "Государство"
        ),
        Tag(
            6, "Авто"
        )
    ),
    mutableListOf(
        Tag(
            7, "Развлечения"
        ),
        Tag(
            8, "Стройка и ремонт"
        )
    )
)

val establishmentCreationTags = mutableListOf(
    mutableListOf(
        Tag(
            1, "WI-FI",
            R.drawable.wifi
        ),
        Tag(
            2, "Розетки",
            R.drawable.zap
        )
    ),
    mutableListOf(
        Tag(
            3, "Телевизоры",
            R.drawable.tv
        ),
        Tag(
            4, "Тихое место",
            R.drawable.headphones
        ),
    ),
    mutableListOf(
        Tag(
            5, "Открытая кухня",
            R.drawable.eye
        ),
        Tag(
            6, "Танцпол",
            R.drawable.music
        ),
    ),
)