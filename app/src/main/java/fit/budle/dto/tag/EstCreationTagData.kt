package fit.budle.dto.tag

import fit.budle.R
import fit.budle.dto.tag.active.RectangleActiveTag

val establishmentCreationType = mutableListOf(
    mutableListOf(
        RectangleActiveTag(
            "Рестораны", 1
        ),
        RectangleActiveTag(
            "Гостиницы", 2
        ),
        RectangleActiveTag(
            "Банки", 3
        )
    ),
    mutableListOf(
        RectangleActiveTag(
            "Медицина", 5
        ),
        RectangleActiveTag(
            "Государство", 6
        ),
        RectangleActiveTag(
            "Авто", 7
        )
    ),
    mutableListOf(
        RectangleActiveTag(
            "Развлечения", 8
        ),
        RectangleActiveTag(
            "Стройка и ремонт", 9
        )
    )
)

val establishmentCreationTags = mutableListOf(
    mutableListOf(
        RectangleActiveTag(
            "WI-FI", 1,
            R.drawable.wifi
        ),
        RectangleActiveTag(
            "Розетки", 2,
            R.drawable.zap
        )
    ),
    mutableListOf(
        RectangleActiveTag(
            "Телевизоры", 3,
            R.drawable.tv
        ),
        RectangleActiveTag(
             "Тихое место", 4,
            R.drawable.headphones
        ),
    ),
    mutableListOf(
        RectangleActiveTag(
            "Открытая кухня", 5,
            R.drawable.eye
        ),
        RectangleActiveTag(
            "Танцпол", 6,
            R.drawable.music
        ),
    ),
)