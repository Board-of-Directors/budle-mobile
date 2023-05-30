package fit.budle.repository.business

import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.result.DeleteEstablishmentResult
import fit.budle.dto.result.PutEstablishmentResult
import fit.budle.request.result.business.OwnerEstResult

interface OwnerMainRepository {
    suspend fun getEstablishmentList(id: Int): OwnerEstResult
    suspend fun putEstablishment(
        establishmentDto: NewEstablishmentDto,
        establishmentId: Int,
    ): PutEstablishmentResult

    suspend fun deleteEstablishment(
        establishmentId: Int,
    ): DeleteEstablishmentResult
}