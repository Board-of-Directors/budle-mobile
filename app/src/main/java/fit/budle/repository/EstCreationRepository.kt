package fit.budle.repository

import fit.budle.dto.establishment.EstablishmentDTO

interface EstCreationRepository {
    suspend fun postEstablishment(establishmentDTO: EstablishmentDTO)
}