package fit.budle.viewmodel.customer

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.di.config.SharedPrefConfig
import fit.budle.dto.UserResponseDto
import fit.budle.event.customer.UserProfileEvent
import fit.budle.repository.customer.BusinessLoginRepository
import fit.budle.repository.customer.RegistrationRepository
import fit.budle.request.result.customer.BusinessLoginResult
import fit.budle.request.result.customer.GetUserResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val businessLoginRepository: BusinessLoginRepository,
    private val userRepository: RegistrationRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    var sessionId: String? by mutableStateOf("")
    var username by mutableStateOf("")
    var buttonText by mutableStateOf("")
    var isLogin by mutableStateOf(false)
    var currentUser by mutableStateOf(UserResponseDto())

    fun onEvent(event: UserProfileEvent) {
        when (event) {
            is UserProfileEvent.PostBusinessLoginEvent -> {
                viewModelScope.launch {
                    when (
                        businessLoginRepository.postBusinessLogin(event.requestUser)
                    ) {
                        is BusinessLoginResult.Success -> {
                            // TODO Доделать
                            Log.d("SESSION_ID", sessionId!!)
                        }

                        else -> {
                            Log.d("USER_PROFILE_VM", "FAILURE")
                        }
                    }
                }
            }

            is UserProfileEvent.Logout -> {
                with(sharedPreferences.edit()) {
                    putString(SharedPrefConfig.sessionId, null)
                    apply()
                }
            }

            is UserProfileEvent.GetSession -> {
                if (sharedPreferences.getString(SharedPrefConfig.sessionId, null) == null) {
                    username = "Вы не вошли в систему"
                    buttonText = "Войти"
                    isLogin = false
                } else {
                    username = sharedPreferences.getString(SharedPrefConfig.username, null)!!
                    buttonText = "Выйти"
                    isLogin = true
                }
            }

            is UserProfileEvent.GetUser -> {
                viewModelScope.launch {
                    when (val response = userRepository.getUser()) {
                        is GetUserResult.Success -> {
                            if (response.result != null) {
                                currentUser = response.result
                            } else Log.w("GET_USER_VM", "NULL")
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}