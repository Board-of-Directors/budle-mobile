package fit.budle.navigation.routes

sealed class NestedGraphRoute(val route: String){
    object EstablishmentCreation: NestedGraphRoute("establishment_creation_route")
    object Registration: NestedGraphRoute("registration_route")
    object Establishments: NestedGraphRoute("establishments_route")
    object UserProfile: NestedGraphRoute("user_profile_route")
    object BusinessProfile: NestedGraphRoute("business_profile_route")
}
