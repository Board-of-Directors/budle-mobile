package fit.budle.event.customer

sealed interface MainEvent {
    object GetEstablishment : MainEvent

    data class EstablishmentRequestParameters(
        var category: String? = null,
        var limit: Int? = null,
        var offset: Int? = null,
        var sortValue: String? = null,
        var name: String? = null,
        var hasCardPayment: Boolean? = null,
        var hasMap: Boolean? = null,
    ) : MainEvent

    object GetFilteredEstablishments : MainEvent
    object GetAllEstablishments : MainEvent
}
