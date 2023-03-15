package nsu.app.budle.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.ui.theme.backgroundError

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