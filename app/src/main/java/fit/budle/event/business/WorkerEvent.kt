package fit.budle.event.business

sealed class WorkerEvent {
    data class DeleteWorker(val establishmentId: Long, val workerId: Long) : WorkerEvent()
    data class AddWorker(val establishmentId: Long, val phoneNumber: String) : WorkerEvent()
    data class GetWorker(val establishmentId: Long) : WorkerEvent()
}