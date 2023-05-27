package fit.budle.repository_impl.customer

import android.content.SharedPreferences
import android.util.Log
import fit.budle.dao.customer.RegistrationDAO
import fit.budle.di.config.SharedPrefConfig
import fit.budle.dto.RegisterType
import fit.budle.dto.code.CodeDto
import fit.budle.dto.customer_user.RequestUser
import fit.budle.repository.customer.RegistrationRepository
import fit.budle.request.result.customer.GetCodeResult
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

        return if (response.body()!!.exception == null) {
            Log.d("POST_USER", "SUCCESS")
            if (response.headers().values("Set-Cookie").isNotEmpty()) {
                with(prefs.edit()) {
                    putString(
                        SharedPrefConfig.sessionId,
                        response.headers().values("Set-Cookie")[0]
                    )
                    putString(SharedPrefConfig.username, requestUserDto.username)
                    apply()
                }
            }
            PostUserResult.Success(
                result = response.body()!!.result,
                exception = response.body()!!.exception
            )
        } else {
            Log.e("POST_USER", response.body()!!.exception!!.message)
            PostUserResult.Failure(response.body()!!.exception!!.message)
        }
    }

    override suspend fun postCode(codeDto: CodeDto): PostCodeResult {
        val response = registrationDAO.postCode(codeDto)
        return if (response.body()!!.exception == null) {
            Log.d("POST_CODE", "SUCCESS")
            PostCodeResult.Success(
                result = response.body()!!.result,
                exception = response.body()!!.exception
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
        return if (response.body()!!.exception == null) {
            Log.d("GET_CODE", "SUCCESS")
            GetCodeResult.Success(
                result = response.body()!!.result,
                exception = response.body()!!.exception
            )
        } else {
            Log.d("GET_CODE", response.body()!!.exception!!.message)
            GetCodeResult.Failure(
                exception = response.body()!!.exception!!.message
            )
        }
    }
}