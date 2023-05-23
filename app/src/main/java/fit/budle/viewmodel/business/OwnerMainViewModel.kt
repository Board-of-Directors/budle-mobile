package fit.budle.viewmodel.business

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.event.business.OwnerMainEvent
import fit.budle.repository.business.OwnerMainRepository
import fit.budle.request.result.business.OwnerEstResult
import kotlinx.coroutines.launch
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