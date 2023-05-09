package fit.budle.repository

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import fit.budle.dto.establishment.*
import fit.budle.dto.result.DefaultResult
import fit.budle.dto.result.OrderListResult
import java.util.HashMap

interface OrderListRepository {
    suspend fun getOrder(userId: Long, status: Int?): OrderListResult

    suspend fun deleteOrder(userId: Long, orderId: Long): DefaultResult
}
