package fit.budle.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import fit.budle.R
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.backgroundError

data class Tab(
    val iconID: Int,
    val text: String,
    val route: String,
    val color: Color?
)

val tabs = mutableStateListOf(/*
    Tab(
        R.drawable.heart,
        "Избранное",
        NavRoute.UserProfileFavorites.route,
        backgroundError
    ),
    Tab(
        R.drawable.settings,
        "Настройки",
        NavRoute.UserProfileSettings.route,
        null
    ),*/
    Tab(
        R.drawable.list,
        "Мои брони",
        "user_profile_bookings",
        null
    ),/*
    Tab(
        R.drawable.message_circle,
        "Мои отзывы",
        NavRoute.UserProfileReviews.route,
        null
    )*/
)