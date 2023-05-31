package fit.budle.viewmodel.customer

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.customer_user.User
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.establishment.EstablishmentDto
import fit.budle.dto.order.Booking
import fit.budle.event.customer.OrderListEvent
import fit.budle.repository.customer.OrderListRepository
import fit.budle.request.result.DefaultResult
import fit.budle.request.result.customer.OrderListResult
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private var repository: OrderListRepository,
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
                                val date =
                                    SimpleDateFormat(
                                        "yyyy-MM-dd",
                                        Locale.getDefault()
                                    ).parse(it.date)
                                val formattedDatesString =
                                    date?.let { it1 ->
                                        SimpleDateFormat(
                                            "LLLL dd, yyyy",
                                            Locale.getDefault()
                                        ).format(
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


        var decodedImage: BitmapPainter? = null;

        if (establishment.image != null) {
            val imageBytes: ByteArray = Base64.decode(establishment.image, Base64.DEFAULT)
            val factory = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            if (factory != null) {
                decodedImage = BitmapPainter(
                    factory.asImageBitmap()
                )
            }
        }

        return Establishment(
            establishment.id,
            establishment.name,
            establishment.description,
            establishment.address,
            User("Олег"),
            hasCardPayment = establishment.hasCardPayment,
            hasMap = establishment.hasMap,
            map = establishment.map,
            establishment.category,
            decodedImage,
            establishment.rating,
            1,
            establishment.workingHours,
            arrayListOf(),
            arrayListOf(),
            establishment.cuisineCountry,
            establishment.starsCount
        )
    }
}
