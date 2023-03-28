package fit.budle.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fit.budle.network.BudleAPIClient
import fit.budle.repository.BudleRepository
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val apiService = BudleAPIClient.service
    private lateinit var repository: BudleRepository
    private var result: String by mutableStateOf("")
    private var dateVar: String by mutableStateOf("")
    private var timeVar: String by mutableStateOf("")
    private var guestCountVar: Int by mutableStateOf(0)

    fun getOrder(establishmentId: Long, userId: Long): String {
        repository = BudleRepository(apiService)
        viewModelScope.launch {
            if (dateVar.isNotEmpty() && timeVar.isNotEmpty() && guestCountVar != 0) {
                dateVar = "1212-12-12"
                timeVar += ":00"
                when (val response = repository.orderRequest(establishmentId, userId, guestCountVar, timeVar, dateVar)) {
                    is BudleRepository.Result.Success -> {
                        Log.d("BOOKVIEWMODEL", "SUCCESS")
                        result =
                            if (response.result == null) "NULL" else if (response.result == true) "TRUE" else "FALSE"
                        Log.e("BOOKSTATUS", result)
                    }
                    is BudleRepository.Result.Failure -> {
                        Log.e("BOOKVIEWMODEL", "FAILURE")
                        response.throwable.message?.let { Log.e("BOOKVIEWMODEL", it) }
                    }
                    else -> {
                        Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                    }
                }
            }
        }
        return result
    }

    fun sendGuestCount(newGuestCount: Int) {
        guestCountVar = newGuestCount
    }
    fun sendTime(newTime: String) {
        timeVar = newTime
    }
    fun sendData(newData: String) {
        dateVar = newData
    }
}
