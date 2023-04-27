package fit.budle.repository_impl

import fit.budle.dao.EstCreationDAO
import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.repository.EstCreationRepository
import javax.inject.Inject

class EstCreationRepositoryImpl @Inject constructor(
    private val estCreationDAO: EstCreationDAO
) : EstCreationRepository {
    override suspend fun postEstablishment(establishmentDTO: EstablishmentDTO) {
            estCreationDAO.postEstablishment(establishmentDTO)
    }
}