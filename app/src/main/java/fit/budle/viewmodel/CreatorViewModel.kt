package fit.budle.viewmodel

import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.worker.Worker
import fit.budle.dto.events.WorkerEvent
import fit.budle.repository.WorkerRepository
import kotlinx.coroutines.launch
import android.os.Handler
import fit.budle.dto.worker.GetWorkerArrayResult
import javax.inject.Inject

@HiltViewModel
class CreatorViewModel @Inject constructor(
    private var workerRepository: WorkerRepository,
) : ViewModel() {

    var state: Array<Worker> by mutableStateOf(emptyArray())

    fun onEvent(event: WorkerEvent) {
        when (event) {
            is WorkerEvent.DeleteWorker -> {
                viewModelScope.launch {
                    workerRepository.deleteWorker(event.establishmentId, event.workerId)
                }
            }
            is WorkerEvent.AddWorker -> {
                viewModelScope.launch {
                    workerRepository.putWorker(event.establishmentId, event.phoneNumber)
                }
            }
            is WorkerEvent.GetWorker -> {
                Handler(Looper.getMainLooper()).postDelayed({
                    getWorkers(2)
                }, 2000)
            }
        }
    }

    private fun getWorkers(id: Long) {
        viewModelScope.launch {
            when (val response = workerRepository.getWorkerArray(id)) {
                is GetWorkerArrayResult.Success -> {
                    state = response.result
                }
                else -> {
                }
            }
        }
    }
}