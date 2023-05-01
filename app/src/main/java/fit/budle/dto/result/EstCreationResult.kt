package fit.budle.dto.result

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.dto.establishment.etsablishment_type.EstablishmentExtendedDto
import fit.budle.dto.tag.standard.TagResponse

sealed interface PostEstablishmentResult {
    data class Success(val result: EstablishmentDTO?, val exception: Exception?) : PostEstablishmentResult
    data class Failure(val throwable: Throwable) : PostEstablishmentResult
}

sealed interface GetCategoryListResult {
    data class Success(val result: List<String>, val exception: Exception?) : GetCategoryListResult
    data class Failure(val throwable: Throwable) : GetCategoryListResult
}

sealed interface GetCategoryVariantListResult {
    data class Success(val result: List<String>, val exception: Exception?) : GetCategoryVariantListResult
    data class Failure(val throwable: Throwable) : GetCategoryVariantListResult
}

sealed interface GetTagListResult {
    data class Success(val result: List<TagResponse>, val exception: Exception?) : GetTagListResult
    data class Failure(val throwable: Throwable) : GetTagListResult
}