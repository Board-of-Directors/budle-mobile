package fit.budle.viewmodel.customer

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.di.establishment.*
import fit.budle.dto.customer_user.User
import fit.budle.dto.events.MainEvent
import fit.budle.dto.tag.standard.Tag
import fit.budle.repository.customer.EstablishmentRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: EstablishmentRepository
) : ViewModel() {

    var categories: Array<String> by mutableStateOf(emptyArray())
    var establishments: HashMap<String, MutableState<EstablishmentArray>> = hashMapOf()

    var establishments2: List<MutableState<EstablishmentArray>> by mutableStateOf(emptyList())
    var establishments3: ArrayList<MutableState<EstablishmentArray>> = arrayListOf()

    var establishmentCard: Establishment by mutableStateOf(Establishment())
    var establishmentCardId: Long by mutableStateOf(1)


    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.getEstablishment -> {
                viewModelScope.launch {
                    when (val response =
                        repository.getEstablishment(establishmentCardId)) {
                        is EstablishmentResult.Success -> {
                            Log.d("MAINVIEWMODEL", "SUCCESS")
                            establishmentCard = convertEstablishment(response.result)
                        }
                        is EstablishmentResult.Failure -> {
                            Log.e("MAINVIEWMODEL", "FAILURE")
                            response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                        }
                    }
                }
            }
            is MainEvent.getEstablishmentALl -> {
                viewModelScope.launch {
                    when (val response = repository.getEstablishmentAll(
                        event.category, event.limit, event.offset, event.sortValue, event.name, event.hasCardPayment, event.hasMap
                    )) {
                        is EstablishmentListResult.Success -> {
                            Log.d("MAINVIEWMODEL", "SUCCESS")
                            if (event.category != null) {
                                establishments[event.category] =
                                    mutableStateOf(EstablishmentArray(response.result.establishments.map {
                                        convertEstablishment(it)
                                    }.toTypedArray(), response.result.count))
                            }
                        }
                        is EstablishmentListResult.Failure -> {
                            Log.e("MAINVIEWMODEL", "FAILURE")
                            response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                        }
                    }
                }
            }
            is MainEvent.getCategory -> {
                viewModelScope.launch {
                    delay(5.seconds)
                    when (val response = repository.getCategory()) {
                        is CategoriesListResult.Success -> {
                            Log.d("MAINVIEWMODEL", "SUCCESS")
                            categories = response.result
                        }
                        is CategoriesListResult.Failure -> {
                            Log.e("MAINVIEWMODEL", "FAILURE")
                            response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                        }
                    }
                    for (category in categories) {
                        when (val response = repository.getEstablishmentAll(
                            category, null, null, null, null, null, null
                        )) {
                            is EstablishmentListResult.Success -> {
                                Log.d("MAINVIEWMODEL", "SUCCESS")
                                establishments3.add(mutableStateOf(EstablishmentArray(response.result.establishments.map {
                                    convertEstablishment(it)
                                }.toTypedArray(), response.result.count)))
                            }
                            is EstablishmentListResult.Failure -> {
                                Log.e("MAINVIEWMODEL", "FAILURE")
                                response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                            }
                        }
                    }
                    establishments2 = establishments3
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
