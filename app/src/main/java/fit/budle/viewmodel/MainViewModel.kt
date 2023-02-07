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

class MainViewModel(phoneNumber: String) : ViewModel() {
    private val apiService = BudleAPIClient.service
    private lateinit var repository: BudleRepository
    var result: String by mutableStateOf("")

    //lateinit var clickedItem: MovieItem

    init {
        getCode(phoneNumber)
    }

    fun getCode(phoneNumber: String) {
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
            }
        }
    }
/*
    fun itemClicked(item: MovieItem) {
        clickedItem = item
    }

 */
}
