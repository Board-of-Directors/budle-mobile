package fit.budle.viewmodel

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fit.budle.dto.tag.standard.Tag
import fit.budle.repository.EstablishmentRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.customer_user.User
import fit.budle.dto.establishment.*
import fit.budle.dto.events.MainEvent
import fit.budle.dto.events.OrderListEvent
import fit.budle.dto.order.Booking
import fit.budle.dto.result.DefaultResult
import fit.budle.dto.result.OrderListResult
import fit.budle.repository.OrderListRepository
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private var repository: OrderListRepository
) : ViewModel() {

    var orders: Array<Booking> by mutableStateOf(emptyArray())

    fun onEvent(event: OrderListEvent) {
        when (event) {
            is OrderListEvent.getOrder -> {
                viewModelScope.launch {
                    when (val response = repository.getOrder(event.userId, event.status)) {
                        is OrderListResult.Success -> {
                            Log.d("ORDERLISTVIEWMODEL", "SUCCESS")
                            response.result.map {
                                it.establishmentImage = convertEstablishment(it.establishment)
                                //it.time = it.time.subSequence(0, it.time.length - 3).toString()
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
                            Log.e("ORDERLISTVIEWMODEL", "FAILURE")
                            response.throwable.message?.let { Log.e("ORDERLISTVIEWMODEL", it) }
                        }
                    }
                }
            }
            is OrderListEvent.deleteOrder -> {
                viewModelScope.launch {
                    when (repository.deleteOrder(event.userId, event.orderId)) {
                        is DefaultResult.Success -> {
                            Log.d("ORDERLISTVIEWMODEL", "SUCCESS")
                        }
                        is DefaultResult.Failure -> {
                            Log.e("ORDERLISTVIEWMODEL", "FAILURE")
                        }
                    }
                }

            }
        }
    }

    private fun convertEstablishment(establishment: EstablishmentDto): Establishment {


        val decodedImage: BitmapPainter? = null //TODO Разобраться с картинками
        val decodedTagsIcons: ArrayList<Tag> = arrayListOf()
/*
        if (establishment.image != null) {
            val imageBytes: ByteArray = Base64.decode(establishment.image, Base64.DEFAULT)
            val factory = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            if (factory != null) {
                decodedImage = BitmapPainter(
                    factory.asImageBitmap()
                )
            }
        }*/

        /* TODO Впилить SVG иконки
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
        */

        return Establishment( //TODO костылиии в значениях
            establishment.id.toLong(),
            establishment.name,
            establishment.description,
            establishment.address,
            User("Олег"),
            hasCardPayment = false,
            hasMap = false,
            establishment.category,
            decodedImage,
            establishment.rating,
            1,
            establishment.workingHours,
            decodedTagsIcons,
            "костыль-кухня",
            2
        )
    }
}
