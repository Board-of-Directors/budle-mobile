package fit.budle.dto.events

sealed class WorkerEvent {
    data class DeleteWorker(val establishmentId: Long, val workerId: Long) : WorkerEvent()
    data class AddWorker(val establishmentId: Long, val phoneNumber: String) : WorkerEvent()
    object GetWorker : WorkerEvent()
}