package fit.budle.repository.business

import fit.budle.request.result.business.OwnerEstResult

interface OwnerMainRepository {
    suspend fun getEstablishmentList(id: Int) : OwnerEstResult
}