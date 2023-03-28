package fit.budle.navigation.routes

sealed class NavRoute(val route: String) {

    object Start : NavRoute("start_screen")
    object Number : NavRoute("number_screen")
    object Code : NavRoute("code_screen")
    object Data : NavRoute("data_screen")
    object End : NavRoute("end_screen")

    object MainPage : NavRoute("main_page")
    object EstablishmentCard : NavRoute("establishment_card_screen")
    object BookingProcess : NavRoute("booking_process_screen")

    object UserProfile : NavRoute("user_profile_screen")
    object UserProfileSettings : NavRoute("user_profile_settings_screen")
    object UserProfileFavorites : NavRoute("user_profile_favorites_screen")
    object UserProfileBookings : NavRoute("user_profile_bookings_screen")
    object UserProfileReviews : NavRoute("user_profile_reviews_screen")

    object BusinessMain : NavRoute("business_main_screen")
    object EstablishmentSettings : NavRoute("establishment_settings_screen")
    object EstablishmentOrders : NavRoute("establishment_orders_screen")
    object EstablishmentWorkers : NavRoute("establishment_workers_screen")

    object EstablishmentCreationFirst : NavRoute("establishment_creation_first")
    object EstablishmentCreationSecond : NavRoute("establishment_creation_second")
    object EstablishmentCreationThird : NavRoute("establishment_creation_third")
    object EstablishmentCreationFourth : NavRoute("establishment_creation_fourth")
}