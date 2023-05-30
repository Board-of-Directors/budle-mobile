package fit.budle.repository.business

import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.result.GetCategoryListResult
import fit.budle.dto.result.GetCategoryVariantListResult
import fit.budle.dto.result.GetTagListResult
import fit.budle.dto.result.PostEstablishmentResult
import fit.budle.dto.result.PutEstablishmentResult

interface EstCreationRepository {
    suspend fun postEstablishment(requestEstablishmentDto: NewEstablishmentDto) : PostEstablishmentResult
    suspend fun getCategoryList() : GetCategoryListResult
    suspend fun getCategoryVariantList(category: String) : GetCategoryVariantListResult
    suspend fun getTagList() : GetTagListResult
}