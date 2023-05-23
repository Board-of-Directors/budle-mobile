package fit.budle.event.customer

sealed interface OrderListEvent {
    data class getOrder(val userId: Long, val status: Int?) : OrderListEvent

    data class deleteOrder(val userId: Long, val orderId: Long) : OrderListEvent
}
