package fit.budle.repository.business

import fit.budle.dto.result.OwnerEstResult

interface OwnerMainRepository {
    suspend fun getEstablishmentList(id: Int) : OwnerEstResult
}