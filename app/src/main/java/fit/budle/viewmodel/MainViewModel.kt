package fit.budle.viewmodel

import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fit.budle.model.Establishment
import fit.budle.model.EstablishmentStructure
import fit.budle.model.EstablishmentWithImage
import fit.budle.model.Tag
import fit.budle.model.*
import fit.budle.models.ordersTagList
import fit.budle.network.BudleAPIClient
import fit.budle.repository.BudleRepository
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap

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

    fun getCard(category: String?, cardID: String?): EstablishmentWithImage? =
        cardID?.let { result3[category]?.value?.establishments?.get(it.toInt()) }

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

    @RequiresApi(Build.VERSION_CODES.N)
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
                        it.time = it.time.subSequence(0, it.time.length - 3).toString()
                        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it.date)
                        val formattedDatesString = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault()).format(date)
                        it.date = formattedDatesString

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

    fun deleteOrderFromUser(userId: Long, orderId: Long) {
        repository = BudleRepository(apiService)
        viewModelScope.launch {
            when (repository.deleteOrderFromUser(userId, orderId)) {
                is BudleRepository.Result.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                }
                is BudleRepository.Result.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
    }


fun convertEstablishment(establishment: Establishment): EstablishmentWithImage {
    var decodedImage: BitmapPainter? = null
    val decodedTagsIcons: ArrayList<Tag> = arrayListOf()
    if (establishment.image != null) {
        val imageBytes: ByteArray = Base64.decode(establishment.image, Base64.DEFAULT)
        decodedImage = BitmapPainter(
            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
        )
    }

    establishment.tags.forEach {
        var decodedIcon: BitmapPainter? = null
        if (it.image != null) {
            val imageBytes: ByteArray = Base64.decode(it.image, Base64.DEFAULT)
            decodedIcon = BitmapPainter(
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
            )
        }
        decodedTagsIcons.add(Tag(name = it.name, image = decodedIcon))
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
        establishment.price,
        establishment.workingHours,
        decodedTagsIcons,
        establishment.cuisineCountry,
        establishment.starsCount
    )
}
