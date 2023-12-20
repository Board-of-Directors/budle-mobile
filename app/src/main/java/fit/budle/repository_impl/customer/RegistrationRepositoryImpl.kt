package fit.budle.repository_impl.customer

import android.content.SharedPreferences
import android.util.Log
import fit.budle.dao.customer.RegistrationDAO
import fit.budle.di.config.SharedPrefConfig
import fit.budle.dto.ResponseException
import fit.budle.dto.code.CodeDto
import fit.budle.dto.customer_user.RequestUser
import fit.budle.dto.enums.RegisterType
import fit.budle.repository.customer.RegistrationRepository
import fit.budle.request.result.customer.GetCodeResult
import fit.budle.request.result.customer.GetUserResult
import fit.budle.request.result.customer.PostCodeResult
import fit.budle.request.result.customer.PostUserResult
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationDAO: RegistrationDAO,
    private val prefs: SharedPreferences,
) : RegistrationRepository {

    override suspend fun postUser(requestUserDto: RequestUser, type: RegisterType): PostUserResult {
        val response = if (type == RegisterType.REGISTER) {
            registrationDAO.postUserRegistration(requestUserDto)
        } else registrationDAO.postUserLogin(requestUserDto)

        return if (response.body() == null) {
            Log.e(
                "POST_USER",
                "SERVER FAILURE: " + response.code() + response.errorBody()?.string().toString()
            )
            PostUserResult.Failure("Ошибка на сервере: " + response.message())
        } else if (response.body()?.exception == null) {
            Log.d("POST_USER", "SUCCESS")
            with(prefs.edit()) {
                if (response.headers().values("Set-Cookie").isNotEmpty()) {
                    putString(
                        SharedPrefConfig.sessionId, response.headers().values("Set-Cookie")[0]
                    )
                }
                putString(SharedPrefConfig.username, requestUserDto.username)
                apply()
            }
            PostUserResult.Success(
                result = response.body()!!.result, exception = response.body()!!.exception
            )
        } else {
            Log.e("POST_USER", "FAILURE: " + response.body()!!.exception!!.message)
            PostUserResult.Failure(response.body()!!.exception!!.message)
        }
    }

    override suspend fun postCode(codeDto: CodeDto): PostCodeResult {
        val response = registrationDAO.postCode(codeDto)
        return if (response.body()!!.exception == null) {
            Log.d("POST_CODE", "SUCCESS")
            PostCodeResult.Success(
                result = response.body()!!.result, exception = response.body()!!.exception
            )
        } else {
            Log.d("POST_CODE", response.body()!!.exception!!.message)
            PostCodeResult.Failure(
                exception = response.body()!!.exception!!.message
            )
        }
    }

    override suspend fun getCode(phoneNumber: String): GetCodeResult {
        val response = registrationDAO.getCode(phoneNumber)
        return if (response.body() == null) {
            Log.e(
                "GET_CODE",
                "SERVER FAILURE: " + response.code() + response.errorBody()?.string().toString()
            )
            GetCodeResult.Failure(
                exception = "Ошибка на сервере"
            )
        } else if (response.body()?.exception == null) {
            Log.d("GET_CODE", "SUCCESS")
            GetCodeResult.Success(
                result = response.body()?.result, exception = response.body()?.exception
            )
        } else {
            Log.e("GET_CODE", "FAILURE: " + response.body()?.exception!!.message)
            GetCodeResult.Failure(
                exception = response.body()?.exception!!.message
            )
        }
    }

    override suspend fun getUser(): GetUserResult {

        val response = registrationDAO.getUser()

        return if (response.body() == null) {

            Log.e("GET_USER", ResponseException.NULL_BODY)
            GetUserResult.Failure(ResponseException.NULL_BODY)

        } else if (response.body()!!.exception == null) {

            Log.d("GET_USER", "SUCCESS")
            GetUserResult.Success(
                result = response.body()!!.result, exception = response.body()!!.exception
            )

        } else {

            Log.d("GET_USER", response.body()!!.exception!!.message)
            GetUserResult.Failure(
                exception = response.body()!!.exception!!.message
            )

        }
    }
}
