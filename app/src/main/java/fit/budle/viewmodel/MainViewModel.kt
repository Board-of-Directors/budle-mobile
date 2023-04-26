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
import fit.budle.dto.establishment.EstablishmentResponse
import fit.budle.dto.establishment.EstablishmentArray
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.tag.active.ordersTagList
import fit.budle.dto.tag.standard.Tag
import fit.budle.repository.EstablishmentRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.establishment.CategoriesListResult
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.dto.establishment.OrderListResult
import fit.budle.dto.establishment.OrderResult
import fit.budle.dto.order.Booking

class MainViewModel (
    private var repository: EstablishmentRepository
) : ViewModel() {

    var categories: Array<String> by mutableStateOf(emptyArray())
    var orders: Array<Booking> by mutableStateOf(emptyArray())
    var categoryMap: HashMap<String, MutableState<EstablishmentArray>> = hashMapOf()

    fun getListOfEstablishments(
        category: String?,
        limit: Int?,
        offset: Int?,
        sortValue: String?,
        name: String?,
        hasCardPayment: Boolean?,
        hasMap: Boolean?,
    ): EstablishmentArray {
        viewModelScope.launch {
            when (val response = repository.getEstablishment(
                category, limit, offset, sortValue, name, hasCardPayment, hasMap
            )) {
                is EstablishmentListResult.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    if (category != null) {
                        categoryMap[category] =
                            mutableStateOf(EstablishmentArray(response.result.establishments.map {
                                convertEstablishment(it)
                            }.toTypedArray(), response.result.count))
                    }
                }
                is EstablishmentListResult.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
        return if (category != null) {
            categoryMap[category]?.value ?: EstablishmentArray()
        } else {
            EstablishmentArray()
        }
    }

    fun getCard(category: String?, cardID: String?): Establishment? =
        cardID?.let { categoryMap[category]?.value?.establishments?.get(it.toInt()) }

    fun getListOfCategories(): Array<String> {
        viewModelScope.launch {
            when (val response = repository.getCategories()) {
                is CategoriesListResult.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                    categories = response.result
                }
                is CategoriesListResult.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
        return categories
    }

    fun getListOfOrders(userId: Long, status: String?): Array<Booking> {
        val map = HashMap<String, Int?>()
        for (tag in ordersTagList) {
            map[tag.tagName] = tag.tagId - 1
        }
        map["Все"] = null
        viewModelScope.launch {
            when (val response = repository.getOrders(userId, map[status])) {
                is OrderListResult.Success -> {
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
                    orders = response.result
                }
                is OrderListResult.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
        return orders
    }

    fun deleteOrderFromUser(userId: Long, orderId: Long) {
        viewModelScope.launch {
            when (repository.deleteOrder(userId, orderId)) {
                is OrderResult.Success -> {
                    Log.d("MAINVIEWMODEL", "SUCCESS")
                }
                is OrderResult.Failure -> {
                    Log.e("MAINVIEWMODEL", "FAILURE")
                }
                else -> {
                    Log.e("CRITICAL_ERROR", "UNDEFINED RESPONSE")
                }
            }
        }
    }

    private fun convertEstablishment(establishment: EstablishmentResponse): Establishment {
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
}
