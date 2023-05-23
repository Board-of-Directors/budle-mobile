package fit.budle.viewmodel.customer

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.di.PrefSettings
import fit.budle.event.customer.UserProfileEvent
import fit.budle.repository.customer.BusinessLoginRepository
import fit.budle.request.result.customer.BusinessLoginResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val businessLoginRepository: BusinessLoginRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    var sessionId: String? by mutableStateOf("")
    var username by mutableStateOf("")
    var buttonText by mutableStateOf("")
    var isLogin by mutableStateOf(false)

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
                    putString(PrefSettings.sessionId, null)
                    apply()
                }
            }

            is UserProfileEvent.GetSession -> {
                if (sharedPreferences.getString(PrefSettings.sessionId, null) == null) {
                    username = "Вы не вошли в систему"
                    buttonText = "Войти"
                    isLogin = false
                } else {
                    username = sharedPreferences.getString(PrefSettings.username, null)!!
                    buttonText = "Выйти"
                    isLogin = true
                }
            }
        }
    }
}