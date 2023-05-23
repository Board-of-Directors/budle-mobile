package fit.budle.event.customer

sealed interface MainEvent {
    object getEstablishment : MainEvent

    data class getEstablishmentALl(
        val category: String?,
        val limit: Int?,
        val offset: Int?,
        val sortValue: String?,
        val name: String?,
        val hasCardPayment: Boolean?,
        val hasMap: Boolean?,
    ) : MainEvent

    object getCategory : MainEvent
}
