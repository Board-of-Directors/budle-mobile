package fit.budle.request.response.business

import fit.budle.dto.Exception
import fit.budle.dto.order.BusinessOrderDto

sealed interface EstOrderResponse {
    data class GetEstOrderListResponse(
        val result: List<BusinessOrderDto>,
        val exception: Exception?,
    ) : EstOrderResponse

    data class PutEstOrderStatusResponse(val result: BusinessOrderDto?, val exception: Exception?) :
        EstOrderResponse
}
