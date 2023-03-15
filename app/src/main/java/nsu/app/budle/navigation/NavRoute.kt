package nsu.app.budle.navigation

sealed class NavRoute(val route: String) {
    object Start : NavRoute("start_screen")
    object Number : NavRoute("number_screen")
    object Code : NavRoute("code_screen")
    object Data : NavRoute("data_screen/{button_name}")
    object End : NavRoute("end_screen")
    object MainList : NavRoute("mainList_screen")
    object UserProfile : NavRoute("userProfile_screen")
    object UserProfileSettings : NavRoute("userProfileSettings_screen")
    object UserProfileFavorites : NavRoute("userProfileFavorites_screen")
    object UserProfileBookings : NavRoute("userProfileBookings_screen")
    object UserProfileReviews : NavRoute("userProfileReviews_screen")
}