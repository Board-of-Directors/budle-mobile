package fit.budle.exeptions

sealed class OrderExceptions(message: String) : Exception(message){
    class OrderRequestNotFoundException : EstablishmentExceptions("Order request not found")
    class OrdersNotFoundException : EstablishmentExceptions("Order list not found")
    class OrderNotFoundException : EstablishmentExceptions("Order not found")
}