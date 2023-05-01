package fit.budle.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.establishment.EstablishmentResponse
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.dto.events.OwnerMainEvent
import fit.budle.dto.result.OwnerEstResult
import fit.budle.repository.OwnerMainRepository
import kotlinx.coroutines.launch
import java.util.stream.Collectors.toList
import javax.inject.Inject

@HiltViewModel
class OwnerMainViewModel @Inject constructor(
    private val ownerMainRepository: OwnerMainRepository,
) : ViewModel() {

    var establishments: List<EstablishmentShortDto> by mutableStateOf(emptyList())

    @RequiresApi(Build.VERSION_CODES.N)
    fun onEvent(event: OwnerMainEvent) {
        when (event) {
            is OwnerMainEvent.GetEstListEvent -> {
                viewModelScope.launch {
                    when (val result = ownerMainRepository.getEstablishmentList(11)) {
                        is OwnerEstResult.Success -> {
                            establishments = result.result.toList()
                        }
                        is OwnerEstResult.Failure -> {
                            Log.d("VM_GET_EST_LIST", result.throwable.message!!)
                        }
                    }
                }
            }
        }
    }
}