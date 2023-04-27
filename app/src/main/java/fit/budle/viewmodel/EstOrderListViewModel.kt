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
import fit.budle.dto.result.DeleteEstOrderResult
import fit.budle.dto.result.GetEstOrderListResult
import fit.budle.dto.result.PutEstOrderResult
import fit.budle.repository.EstOrderListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstOrderListViewModel @Inject constructor(
    private val estOrderListRepository: EstOrderListRepository,
) : ViewModel() {

    var state: List<Booking> by mutableStateOf(emptyList())

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
            is BookingEvent.PutBooking -> {
                viewModelScope.launch {
                    when (val result = estOrderListRepository.putOrder(
                        event.establishmentId,
                        event.orderId,
                        event.status
                    )) {
                        is PutEstOrderResult.Success -> {
                            Log.d("VM_PUT_EST_ORDER", "SUCCESS")
                        }
                        is PutEstOrderResult.Failure -> {
                            Log.d("VM_PUT_EST_ORDER", result.throwable.message!!)
                        }
                    }
                }
            }
            is BookingEvent.DeleteBooking -> {
                viewModelScope.launch {
                    when (val result = estOrderListRepository.deleteOrder(
                        event.establishmentId,
                        event.orderId
                    )) {
                        is DeleteEstOrderResult.Success -> {
                            Log.d("DELETE_EST_ORDER", "SUCCESS")
                        }
                        is DeleteEstOrderResult.Failure -> {
                            Log.d("DELETE_EST_ORDER", result.throwable.message!!)
                        }
                    }
                }
            }
        }
    }
}