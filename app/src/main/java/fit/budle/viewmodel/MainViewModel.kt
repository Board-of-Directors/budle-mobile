package fit.budle.viewmodel

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fit.budle.model.*
import fit.budle.models.ordersTagList
import fit.budle.network.BudleAPIClient
import fit.budle.repository.BudleRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val apiService = BudleAPIClient.service
    private lateinit var repository: BudleRepository
    var result2: Array<String> by mutableStateOf(emptyArray())
    var result: EstablishmentStructure by mutableStateOf(EstablishmentStructure(emptyArray(), 0))
    var result3: HashMap<String, MutableState<EstablishmentStructure>> = hashMapOf()
    var result4: Array<Booking> by mutableStateOf(emptyArray())

    fun getListOfEstablishments(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentStructure {
        repository = BudleRepository(apiService)
        viewModelScope.launch {
            when (val response = repository.getEstablishmentsRequest(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )) {
                is BudleRepository.ResultList.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    if (category != null) {
                        result3[category] =
                            mutableStateOf(EstablishmentStructure(response.result.establishments.map {
                                convertEstablishment(it)
                            }.toTypedArray(), response.result.count))
                    }
                }
                is BudleRepository.ResultList.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
        if (category != null) {
            return result3[category]?.value ?: result
        } else {
            return result
        }
    }

    fun getListOfCategories(): Array<String> {
        repository = BudleRepository(apiService)
        viewModelScope.launch {
            when (val response = repository.getCategoriesRequest()) {
                is BudleRepository.ResultList2.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    result2 = response.result
                }
                is BudleRepository.ResultList2.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
        return result2
    }

    fun getListOfOrders(userId: Long, status: String?): Array<Booking> {
        repository = BudleRepository(apiService)
        val map = HashMap<String, Int?>()
        for (tag in ordersTagList) {
            map[tag.tagName] = tag.tagId - 1
        }
        map["Все"] = null
        viewModelScope.launch {
            when (val response = repository.getOrdersRequest(userId, map[status])) {
                is BudleRepository.ResultList3.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    response.result.map {
                        it.establishmentImage = convertEstablishment(it.establishment)
                    }
                    result4 = response.result
                }
                is BudleRepository.ResultList3.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
        return result4
    }


    fun convertEstablishment(establishment: Establishment): EstablishmentWithImage {
        val imageBytes: ByteArray?
        var decodedImage: BitmapPainter? = null
        if (establishment.image != null) {
            imageBytes = Base64.decode(establishment.image, Base64.DEFAULT)
            decodedImage = BitmapPainter(
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
            )
        }

        return EstablishmentWithImage(
            establishment.id,
            establishment.name,
            establishment.description,
            establishment.address,
            establishment.owner,
            establishment.hasCardPayment,
            establishment.hasMap,
            establishment.category,
            decodedImage,
            establishment.rating,
            establishment.price
        )
    }
}
