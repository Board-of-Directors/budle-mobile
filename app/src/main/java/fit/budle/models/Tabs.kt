package fit.budle.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import fit.budle.R
import fit.budle.ui.theme.backgroundError
import fit.budle.navigation.NavRoute

data class Tab(
    val iconID: Int,
    val text: String,
    val route: String,
    val color: Color?
)

val tabs = mutableStateListOf(
    Tab(
        R.drawable.heart,
        "Избранное",
        "user_profile_favorites",
        backgroundError
    ),
    Tab(
        R.drawable.settings,
        "Настройки",
        "user_profile_settings",
        null
    ),
    Tab(
        R.drawable.list,
        "Мои брони",
        "user_profile_bookings",
        null
    ),
    Tab(
        R.drawable.message_circle,
        "Мои отзывы",
        "user_profile_reviews",
        null
    )
)