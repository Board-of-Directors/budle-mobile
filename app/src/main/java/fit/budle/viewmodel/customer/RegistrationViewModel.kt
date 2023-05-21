package fit.budle.viewmodel.customer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.RegisterType
import fit.budle.dto.code.CodeDto
import fit.budle.dto.customer_user.RequestUser
import fit.budle.event.customer.RegistrationEvent
import fit.budle.repository.customer.RegistrationRepository
import fit.budle.request.result.customer.GetCodeResult
import fit.budle.request.result.customer.PostCodeResult
import fit.budle.request.result.customer.PostUserResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationRepository: RegistrationRepository,
) : ViewModel() {

    private var sessionId by mutableStateOf("")

    var type by mutableStateOf(RegisterType.REGISTER)
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var states = mutableStateListOf("", "", "", "")

    var requestException: String by mutableStateOf("")

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.GetCode -> {
                viewModelScope.launch {
                    requestException =
                        when (val result = registrationRepository.getCode(phoneNumber)) {
                            is GetCodeResult.Success -> ""
                            is GetCodeResult.Failure -> result.exception
                        }
                }
            }
            is RegistrationEvent.PostCode -> {
                viewModelScope.launch {
                    requestException = when (val result = registrationRepository.postCode(
                        CodeDto(
                            code = states.joinToString(""),
                            phoneNumber = phoneNumber
                        )
                    )) {
                        is PostCodeResult.Success -> ""
                        is PostCodeResult.Failure -> result.exception
                    }
                }
            }
            is RegistrationEvent.PostUser -> {
                viewModelScope.launch {
                    when (val result = registrationRepository.postUser(
                        RequestUser(
                            password = password,
                            phoneNumber = phoneNumber,
                            username = username
                        ), event.type
                    )) {
                        is PostUserResult.Success -> {
                            sessionId = result.headers
                                .values("Set-Cookie")[0]
                                .split(";")[0]
                                .split("=")[1]
                            requestException = ""
                        }
                        is PostUserResult.Failure -> {
                            requestException = result.exception
                        }
                    }
                }
            }
            is RegistrationEvent.Login -> {
                type = RegisterType.LOGIN
            }
        }
    }
}