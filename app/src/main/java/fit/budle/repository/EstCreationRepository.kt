package fit.budle.repository

import fit.budle.dto.establishment.EstablishmentDtoResponseDto
import fit.budle.dto.result.GetCategoryListResult
import fit.budle.dto.result.GetCategoryVariantListResult
import fit.budle.dto.result.GetTagListResult
import fit.budle.dto.result.PostEstablishmentResult

interface EstCreationRepository {
    suspend fun postEstablishment(requestEstablishmentDto: EstablishmentDtoResponseDto) : PostEstablishmentResult
    suspend fun getCategoryList() : GetCategoryListResult
    suspend fun getCategoryVariantList(category: String) : GetCategoryVariantListResult
    suspend fun getTagList() : GetTagListResult
}