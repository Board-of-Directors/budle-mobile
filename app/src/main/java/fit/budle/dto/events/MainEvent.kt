package fit.budle.dto.events

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

    data class getOrder(val userId: Long, val status: Int?) : MainEvent

    data class postOrder(
        val establishmentId: Long,
        val userId: Long,
        val guestCount: Int,
        val time: String,
        val date: String,
    ) : MainEvent

    data class deleteOrder(val userId: Long, val orderId: Long) : MainEvent
}
