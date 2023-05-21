package fit.budle.viewmodel.customer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.event.customer.UserProfileEvent
import fit.budle.repository.customer.BusinessLoginRepository
import fit.budle.request.result.customer.BusinessLoginResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val businessLoginRepository: BusinessLoginRepository,
) : ViewModel() {

    var sessionId: String? by mutableStateOf("")

    fun onEvent(event: UserProfileEvent) {
        when (event) {
            is UserProfileEvent.PostBusinessLoginEvent -> {
                viewModelScope.launch {
                    when (
                        val result = businessLoginRepository.postBusinessLogin(event.requestUser)
                    ) {
                        is BusinessLoginResult.Success -> {
                            sessionId = result.headers
                                .values("Set-Cookie")[0]
                                .split(";")[0]
                                .split("=")[1]
                            Log.d("SESSION_ID", sessionId!!)
                        }
                        else -> {
                            Log.d("USER_PROFILE_VM", "FAILURE")
                        }
                    }
                }
            }
        }
    }
}