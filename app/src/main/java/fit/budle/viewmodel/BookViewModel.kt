package fit.budle.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private var repository: Repository
): ViewModel() {

    private var result: String by mutableStateOf("")
    private var dateVar: String by mutableStateOf("")
    private var timeVar: String by mutableStateOf("")
    private var guestCountVar: Int by mutableStateOf(1)

    fun getOrder(establishmentId: Long, userId: Long): String {
        viewModelScope.launch {
            Log.e("GUEST", guestCountVar.toString())
            if (dateVar.isNotEmpty() && timeVar.isNotEmpty()) {
                dateVar = "2022-03-$dateVar"
                timeVar += ":00"

                when (val response = repository.orderRequest(
                    establishmentId,
                    userId,
                    guestCountVar,
                    timeVar,
                    dateVar
                )) {
                    is Repository.Result.Success -> {
                        Log.d("BOOKVIEWMODEL", "SUCCESS")
                        result =
                            if (response.result == null) "NULL" else if (response.result == true) "TRUE" else "FALSE"
                        Log.e("BOOKSTATUS", result)
                    }
                    is Repository.Result.Failure -> {
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
