package fit.budle.repository.customer

import fit.budle.dto.RegisterType
import fit.budle.dto.code.CodeDto
import fit.budle.dto.customer_user.RequestUser
import fit.budle.request.result.customer.GetCodeResult
import fit.budle.request.result.customer.PostCodeResult
import fit.budle.request.result.customer.PostUserResult

interface RegistrationRepository {
    suspend fun postUser(requestUserDto: RequestUser, type: RegisterType): PostUserResult

    suspend fun postCode(codeDto: CodeDto): PostCodeResult

    suspend fun getCode(phoneNumber: String): GetCodeResult
}