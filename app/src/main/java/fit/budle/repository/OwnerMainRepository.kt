package fit.budle.repository

import fit.budle.dto.result.OwnerEstResult

interface OwnerMainRepository {
    suspend fun getEstablishmentList() : OwnerEstResult
}