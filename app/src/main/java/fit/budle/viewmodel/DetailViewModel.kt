package fit.budle.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fit.budle.network.BudleAPIClient
import fit.budle.repository.BudleRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val apiService = BudleAPIClient.service
    private lateinit var repository: BudleRepository
    var result: String by mutableStateOf("")

    fun getCode(phoneNumber: String): String {
        repository = BudleRepository(apiService)
        viewModelScope.launch {
            when (val response = repository.getCode(phoneNumber)) {
                is BudleRepository.Result.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    result =
                        if (response.result == null) "NULL" else if (response.result == true) "TRUE" else "FALSE"
                }
                is BudleRepository.Result.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
        return result
    }
}
