package fit.budle.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.events.BookingEvent
import fit.budle.dto.order.Booking
import fit.budle.dto.order.BusinessOrderDto
import fit.budle.dto.result.*
import fit.budle.repository.EstOrderListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstOrderListViewModel @Inject constructor(
    private val estOrderListRepository: EstOrderListRepository,
) : ViewModel() {

    var state: List<BusinessOrderDto> by mutableStateOf(emptyList())

    fun onEvent(event: BookingEvent) {
        when (event) {
            is BookingEvent.GetBookings -> {
                viewModelScope.launch {
                    when (val result =
                        estOrderListRepository.getOrderList(event.establishmentId, event.status)) {
                        is GetEstOrderListResult.Success -> {
                            state = result.result
                        }
                        is GetEstOrderListResult.Failure -> {
                            Log.d("VM_EST_ORDER_LIST_GET", result.throwable.message!!)
                        }
                    }
                }
            }
            is BookingEvent.AcceptBooking -> {
                viewModelScope.launch {
                    when (val result = estOrderListRepository.acceptOrder(
                        event.establishmentId,
                        event.orderId
                    )) {
                        is AcceptEstOrderResult.Success -> {
                            Log.d("VM_PUT_EST_ORDER", "SUCCESS")
                        }
                        is AcceptEstOrderResult.Failure -> {
                            Log.d("VM_PUT_EST_ORDER", result.throwable.message!!)
                        }
                    }
                }
            }
            is BookingEvent.RejectBooking -> {
                viewModelScope.launch {
                    when (val result = estOrderListRepository.rejectOrder(
                        event.establishmentId,
                        event.orderId
                    )) {
                        is RejectEstOrderResult.Success -> {
                            Log.d("DELETE_EST_ORDER", "SUCCESS")
                        }
                        is RejectEstOrderResult.Failure -> {
                            Log.d("DELETE_EST_ORDER", result.throwable.message!!)
                        }
                    }
                }
            }
        }
    }
}