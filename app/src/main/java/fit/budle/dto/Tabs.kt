package fit.budle.dto

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import fit.budle.R

data class Tab(
    val iconID: Int,
    val text: String,
    val route: String,
    val color: Color?
)

val tabs = mutableStateListOf(
/*
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
        "userProfile/bookings",
        null
    ),/*
    Tab(
        R.drawable.message_circle,
        "Мои отзывы",
        NavRoute.UserProfileReviews.route,
        null
    )*/
)