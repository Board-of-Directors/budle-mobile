package fit.budle.viewmodel.business

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.establishment.EstablishmentResult
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.event.business.OwnerMainEvent
import fit.budle.repository.business.OwnerMainRepository
import fit.budle.repository.customer.EstablishmentRepository
import fit.budle.request.result.business.OwnerEstResult
import fit.budle.util.EstablishmentConverter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerMainViewModel @Inject constructor(
    private val ownerMainRepository: OwnerMainRepository,
    private val establishmentRepository: EstablishmentRepository,
) : ViewModel() {

    var establishments: List<EstablishmentShortDto> by mutableStateOf(emptyList())

    var currentEstablishment by mutableStateOf<Establishment?>(null)
    var editedEstablishment by mutableStateOf(NewEstablishmentDto())
    var establishmentId by mutableStateOf(0)

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

            is OwnerMainEvent.GetEstablishment -> {
                viewModelScope.launch {
                    when (val response =
                        establishmentRepository.getEstablishment(establishmentId.toLong())
                    ) {
                        is EstablishmentResult.Success -> {
                            currentEstablishment =
                                EstablishmentConverter.convertEstablishment(
                                    response.result,
                                    viewModelScope
                                )
                        }

                        else -> {}
                    }
                }
            }

            is OwnerMainEvent.PutEstablishment -> {
                viewModelScope.launch {
                    ownerMainRepository.putEstablishment(editedEstablishment, establishmentId)
                }
            }

            is OwnerMainEvent.DeleteEstablishment -> {
                viewModelScope.launch {
                    ownerMainRepository.deleteEstablishment(establishmentId)
                }
            }
        }
    }
}