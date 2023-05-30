package fit.budle.request.response

import fit.budle.dto.Exception
import fit.budle.dto.UserResponseDto

data class UserResponse(val result: UserResponseDto?, val exception: Exception?)
