package fit.budle.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import fit.budle.R
import fit.budle.ui.theme.backgroundError
import fit.budle.navigation.NavRoute

data class Tab(
    val iconID: Int,
    val text: String,
    val color: Color?,
    val route: String
)

val tabs = mutableStateListOf(
    Tab(
        R.drawable.heart,
        "Избранное",
        backgroundError,
        NavRoute.UserProfileFavorites.route
    ),
    Tab(
        R.drawable.settings,
        "Настройки",
        null,
        NavRoute.UserProfileSettings.route
    ),
    Tab(
        R.drawable.list,
        "Мои брони",
        null,
        NavRoute.UserProfileBookings.route
    ),
    Tab(
        R.drawable.message_circle,
        "Мои отзывы",
        null,
        NavRoute.UserProfileReviews.route
    )
)