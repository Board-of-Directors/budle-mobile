package fit.budle.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.dto.events.OwnerMainEvent
import fit.budle.repository.EstablishmentRepository
import fit.budle.repository.OwnerEstablishmentRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerMainViewModel @Inject constructor(
    private var ownerEstablishmentRepository: OwnerEstablishmentRepository,
) : ViewModel() {

    var state: Array<EstablishmentDTO> by mutableStateOf(emptyArray())

    fun onEvent(event: OwnerMainEvent) {
        when (event) {
            is OwnerMainEvent.GetEstablishmentsEvent -> {
                viewModelScope.launch {
                    ownerEstablishmentRepository.getEstablishmentArray()
                }
            }
        }
    }
}