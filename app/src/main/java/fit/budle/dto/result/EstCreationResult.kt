package fit.budle.dto.result

import fit.budle.di.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.Exception
import fit.budle.dto.tag.standard.TagResponse

sealed interface PostEstablishmentResult {
    data class Success(val result: NewEstablishmentDto?, val exception: Exception?) : PostEstablishmentResult
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