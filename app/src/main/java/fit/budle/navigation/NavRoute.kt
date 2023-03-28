package fit.budle.navigation

sealed class NavRoute(val route: String) {
    object MainPage : NavRoute("main_page")
}
