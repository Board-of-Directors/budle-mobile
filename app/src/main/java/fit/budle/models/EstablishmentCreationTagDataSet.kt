package fit.budle.models

import fit.budle.R

val restaurantsType = mutableListOf(
    mutableListOf<Tag>(
        RectangleTag(
            "Грузинская",
            1
        ),
        RectangleTag(
            "Европейская",
            2
        )
    ),
    mutableListOf<Tag>(
        RectangleTag(
            "Азиатская",
            3
        ),
        RectangleTag(
            "Русская",
            4
        ),
        RectangleTag(
            "Вьетнамская",
            5
        )
    )
)

val startsType = mutableListOf(
    mutableListOf<Tag>(
        RectangleTag(
            "1 звезда",
            1
        ),
        RectangleTag(
            "2 звезды",
            2
        )
    ),
    mutableListOf<Tag>(
        RectangleTag(
            "3 звезды",
            3
        ),
        RectangleTag(
            "4 звезды",
            4
        ),
        RectangleTag(
            "5 звезд",
            5
        )
    )
)

val establishmentCreationType = mutableListOf(
    mutableListOf<Tag>(
        RectangleTag(
            "Рестораны",
            1
        ),
        RectangleTag(
            "Гостиницы",
            2
        ),
        RectangleTag(
            "Банки",
            3
        )
    ),
    mutableListOf<Tag>(
        RectangleTag(
            "Медицина",
            4
        ),
        RectangleTag(
            "Государство",
            5
        ),
        RectangleTag(
            "Авто",
            6
        )
    ),
    mutableListOf<Tag>(
        RectangleTag(
            "Развлечения",
            7
        ),
        RectangleTag(
            "Стройка и ремонт",
            8
        )
    )
)

val establishmentCreationTags = mutableListOf(
    mutableListOf<Tag>(
        InfoTag(
            "WI-FI",
            1,
            R.drawable.wifi
        ),
        InfoTag(
            "Розетки",
            2,
            R.drawable.zap
        )
    ),
    mutableListOf<Tag>(
        InfoTag(
            "Телевизоры",
            3,
            R.drawable.tv
        ),
        InfoTag(
            "Тихое место",
            4,
            R.drawable.headphones
        ),
    ),
    mutableListOf<Tag>(
        InfoTag(
            "Открытая кухня",
            5,
            R.drawable.eye
        ),
        InfoTag(
            "Танцпол",
            6,
            R.drawable.music
        ),
    ),
)