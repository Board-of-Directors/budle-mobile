package fit.budle.request.result.customer

import fit.budle.dto.Exception
import fit.budle.dto.UserResponseDto

sealed interface PostUserResult {
    data class Success(val result: Boolean?, val exception: Exception?) :
        PostUserResult

    data class Failure(val exception: String) : PostUserResult
}

sealed interface PostCodeResult {
    data class Success(val result: Boolean?, val exception: Exception?) :
        PostCodeResult

    data class Failure(val exception: String) : PostCodeResult
}

sealed interface GetCodeResult {
    data class Success(val result: Boolean?, val exception: Exception?) :
        GetCodeResult

    data class Failure(val exception: String) : GetCodeResult
}

sealed interface GetUserResult {
    data class Success(val result: UserResponseDto?, val exception: Exception?) :
        GetUserResult

    data class Failure(val exception: String) : GetUserResult
}



