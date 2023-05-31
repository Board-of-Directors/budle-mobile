package fit.budle.viewmodel.business

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.order.BusinessOrderDto
import fit.budle.dto.result.GetEstOrderListResult
import fit.budle.dto.result.PutEstOrderStatusResult
import fit.budle.event.business.BookingEvent
import fit.budle.repository.business.EstOrderListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstOrderListViewModel @Inject constructor(
    private val estOrderListRepository: EstOrderListRepository,
) : ViewModel() {

    var state: List<BusinessOrderDto> by mutableStateOf(emptyList())
    var currentType by mutableStateOf(3)

    fun onEvent(event: BookingEvent) {
        when (event) {
            is BookingEvent.GetBookings -> {
                viewModelScope.launch {
                    when (val result =
                        estOrderListRepository.getOrderList(event.establishmentId, currentType)) {
                        is GetEstOrderListResult.Success -> {
                            state = result.result
                        }

                        is GetEstOrderListResult.Failure -> {
                            Log.d("VM_EST_ORDER_LIST_GET", result.throwable.message!!)
                        }
                    }
                }
            }
            is BookingEvent.PutBookingStatus -> {
                viewModelScope.launch {
                    when (val result = estOrderListRepository.putOrderStatus(
                        event.establishmentId,
                        event.orderId,
                        event.status
                    )) {
                        is PutEstOrderStatusResult.Success -> {
                            Log.d("VM_PUT_EST_ORDER", "SUCCESS")
                        }
                        is PutEstOrderStatusResult.Failure -> {
                            Log.d("VM_PUT_EST_ORDER", result.throwable.message!!)
                        }
                    }
                }
            }
        }
    }
}