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
import fit.budle.dto.customer_user.User
import fit.budle.dto.establishment.CategoriesListResult
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.establishment.EstablishmentArray
import fit.budle.dto.establishment.EstablishmentDto
import fit.budle.dto.establishment.EstablishmentListResult
import fit.budle.dto.establishment.EstablishmentResult
import fit.budle.dto.tag.standard.Tag
import fit.budle.event.customer.MainEvent
import fit.budle.repository.customer.EstablishmentRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: EstablishmentRepository,
) : ViewModel() {

    private var categories: Array<String> by mutableStateOf(emptyArray())

    var establishmentsForScreen: ArrayList<MutableState<EstablishmentArray>> = arrayListOf()

    var establishmentCard: Establishment by mutableStateOf(Establishment())
    var establishmentCardId: Long by mutableStateOf(1L)


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
                        event.category,
                        event.limit,
                        event.offset,
                        event.sortValue,
                        event.name,
                        event.hasCardPayment,
                        event.hasMap
                    )) {
                        is EstablishmentListResult.Success -> {
                            Log.d("MAINVIEWMODEL", "SUCCESS")
                            establishmentsForScreen.add(
                                mutableStateOf(
                                    EstablishmentArray(
                                        response.result.establishments.map {
                                            convertEstablishment(it)
                                        }.toTypedArray(), response.result.count
                                    )
                                )
                            )


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

                    establishmentsForScreen = ArrayList()
                    for (category in categories) {
                        viewModelScope.launch {
                            when (val response = repository.getEstablishmentAll(
                                category, null, null, null, null, null, null
                            )) {
                                is EstablishmentListResult.Success -> {
                                    Log.d("MAINVIEWMODEL", "SUCCESS")
                                    establishmentsForScreen.add(
                                        mutableStateOf(
                                            EstablishmentArray(
                                                response.result.establishments.map {
                                                    convertEstablishment(it)
                                                }.toTypedArray(), response.result.count
                                            )
                                        )
                                    )
                                }

                                is EstablishmentListResult.Failure -> {
                                    Log.e("MAINVIEWMODEL", "FAILURE")
                                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                                }
                            }
                        }
                    }
                    establishmentsForScreen.sortBy { mutableState ->
                        mutableState.value.establishments[0].category;
                    }
                    delay(1200.seconds)
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

        return Establishment(
            establishment.id,
            establishment.name,
            establishment.description,
            establishment.address,
            User("Олег"),
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
