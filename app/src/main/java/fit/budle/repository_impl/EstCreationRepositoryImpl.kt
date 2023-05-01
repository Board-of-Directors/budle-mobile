package fit.budle.repository_impl

import android.util.Log
import fit.budle.dao.EstCreationDAO
import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.dto.result.GetCategoryListResult
import fit.budle.dto.result.GetCategoryVariantListResult
import fit.budle.dto.result.GetTagListResult
import fit.budle.dto.result.PostEstablishmentResult
import fit.budle.repository.EstCreationRepository
import javax.inject.Inject

class EstCreationRepositoryImpl @Inject constructor(
    private val estCreationDAO: EstCreationDAO,
) : EstCreationRepository {

    override suspend fun postEstablishment(requestEstablishmentDto: EstablishmentDTO): PostEstablishmentResult {
        return try {
            val response = estCreationDAO.postEstablishment(requestEstablishmentDto)
            Log.d("POST_ESTABLISHMENT", "SUCCESS")
            PostEstablishmentResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("POST_ESTABLISHMENT", e.message!!)
            PostEstablishmentResult.Failure(e)
        }
    }

    override suspend fun getCategoryList(): GetCategoryListResult {
        return try {
            val response = estCreationDAO.getCategoryList()
            Log.d("GET_CATEGORY_LIST", "SUCCESS")
            GetCategoryListResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("GET_CATEGORY_LIST", e.message!!)
            GetCategoryListResult.Failure(e)
        }
    }

    override suspend fun getCategoryVariantList(category: String): GetCategoryVariantListResult {
        return try {
            val response = estCreationDAO.getCategoryVariantList(category)
            Log.d("GET_VARIANTS_LIST", "SUCCESS")
            GetCategoryVariantListResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("GET_VARIANTS_LIST", e.message!!)
            GetCategoryVariantListResult.Failure(e)
        }
    }

    override suspend fun getTagList(): GetTagListResult {
        return try {
            val response = estCreationDAO.getTagList()
            Log.d("GET_TAG_LIST", "SUCCESS")
            GetTagListResult.Success(
                result = response.result,
                exception = response.exception
            )
        } catch (e: Throwable) {
            Log.d("GET_TAG_LIST", e.message!!)
            GetTagListResult.Failure(e)
        }
    }
}