package fit.budle.repository.customer

import fit.budle.dto.customer_user.RequestUser
import fit.budle.request.result.customer.BusinessLoginResult

interface BusinessLoginRepository {
    suspend fun postBusinessLogin(requestUser: RequestUser): BusinessLoginResult
}