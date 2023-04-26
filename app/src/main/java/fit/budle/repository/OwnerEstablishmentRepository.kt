package fit.budle.repository

import fit.budle.dto.establishment.EstablishmentListResult

interface OwnerEstablishmentRepository {
    suspend fun getEstablishmentArray() : EstablishmentListResult
}