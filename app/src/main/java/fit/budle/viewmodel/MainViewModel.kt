package fit.budle.viewmodel

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fit.budle.model.*
import fit.budle.model.establishment.EstablishmentResponse
import fit.budle.model.establishment.EstablishmentArray
import fit.budle.model.establishment.Establishment
import fit.budle.model.tag.active.ordersTagList
import fit.budle.model.tag.standard.Tag
import fit.budle.network.APIClient
import fit.budle.repository.Repository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {

    private val apiService = APIClient.service
    private lateinit var repository: Repository
    var result2: Array<String> by mutableStateOf(emptyArray())
    var result: EstablishmentArray by mutableStateOf(EstablishmentArray(emptyArray(), 0))
    var result3: HashMap<String, MutableState<EstablishmentArray>> = hashMapOf()
    var result4: Array<Booking> by mutableStateOf(emptyArray())

    fun getListOfEstablishments(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentArray {
        repository = Repository(apiService)
        viewModelScope.launch {
            when (val response = repository.getEstablishmentsRequest(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )) {
                is Repository.ResultList.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    if (category != null) {
                        result3[category] =
                            mutableStateOf(EstablishmentArray(response.result.establishments.map {
                                convertEstablishment(it)
                            }.toTypedArray(), response.result.count))
                    }
                }
                is Repository.ResultList.Failure -> {
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

    fun getCard(category: String?, cardID: String?): Establishment? =
        cardID?.let { result3[category]?.value?.establishments?.get(it.toInt()) }

    fun getListOfCategories(): Array<String> {
        repository = Repository(apiService)
        viewModelScope.launch {
            when (val response = repository.getCategoriesRequest()) {
                is Repository.ResultList2.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    result2 = response.result
                }
                is Repository.ResultList2.Failure -> {
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
        repository = Repository(apiService)
        val map = HashMap<String, Int?>()
        for (tag in ordersTagList) {
            map[tag.tagName] = tag.tagId - 1
        }
        map["Все"] = null
        viewModelScope.launch {
            when (val response = map[status]?.let { repository.getOrders(userId, it) }) {
                is Repository.ResultList3.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    response.result.map {
                        it.establishmentImage = convertEstablishment(it.establishment)
                        it.time = it.time.subSequence(0, it.time.length - 3).toString()
                        val date =
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it.date)
                        val formattedDatesString =
                            date?.let { it1 ->
                                SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault()).format(
                                    it1
                                )
                            }
                        if (formattedDatesString != null) {
                            it.date = formattedDatesString
                        }

                    }
                    result4 = response.result
                }
                is Repository.ResultList3.Failure -> {
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
        repository = Repository(apiService)
        viewModelScope.launch {
            when (repository.deleteOrderFromUser(userId, orderId)) {
                is Repository.Result.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                }
                is Repository.Result.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
    }
}

fun convertEstablishment(establishment: EstablishmentResponse): Establishment {
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

    return Establishment(
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
