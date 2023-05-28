package fit.budle.viewmodel.customer

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: EstablishmentRepository,
) : ViewModel() {

    private var categories: Array<String> by mutableStateOf(emptyArray())

    private val parametersEstablishmentRequest = MainEvent.EstablishmentRequestParameters();

    var establishmentsForScreen = mutableStateListOf<EstablishmentArray>()

    var establishmentCard: Establishment by mutableStateOf(Establishment())
    var establishmentCardId: Long by mutableStateOf(1L)


    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetEstablishment -> {
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

            is MainEvent.GetAllEstablishments -> {
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
                    for (category in categories) {
                        parametersEstablishmentRequest.category = category
                        viewModelScope.launch {
                            when (val response =
                                repository.getEstablishmentAll(
                                    category, null, null, null, null, null, null
                                )) {
                                is EstablishmentListResult.Success -> {
                                    Log.d("MAINVIEWMODEL", "SUCCESS")
                                    val establishmentArray = EstablishmentArray(
                                        response.result.establishments.map {
                                            convertEstablishment(it)
                                        }.toTypedArray(), response.result.count
                                    )

                                    if (!establishmentsForScreen.contains(establishmentArray)) {
                                        establishmentsForScreen.add(establishmentArray)
                                    } else {
                                        establishmentsForScreen.remove(establishmentArray)
                                        establishmentsForScreen.add(establishmentArray)
                                    }
                                    establishmentsForScreen.sortBy { array ->
                                        array.establishments.first().category
                                    }
                                }

                                is EstablishmentListResult.Failure -> {
                                    Log.e("MAINVIEWMODEL", "FAILURE")
                                    response.throwable.message?.let { Log.e("MAINVIEWMODEL", it) }
                                }
                            }
                        }
                    }
                }
            }

            else -> {}
        }
    }

    private fun convertEstablishment(establishment: EstablishmentDto): Establishment {

        var decodedImage: BitmapPainter? = null
        val decodedTagsIcons: ArrayList<Tag> = arrayListOf()
        var decodedPhotos: ArrayList<BitmapPainter?> = arrayListOf()
        viewModelScope.launch {

            if (establishment.image != null) {
                val imageBytes: ByteArray = Base64.decode(establishment.image, Base64.DEFAULT)
                val factory = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                if (factory != null) {
                    decodedImage = BitmapPainter(
                        factory.asImageBitmap()
                    )
                }
            }
            if (establishment.photos != null) {
                for (photo in establishment.photos) {
                    val imageBytes: ByteArray = Base64.decode(photo, Base64.DEFAULT)
                    val factory = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    if (factory != null) {
                        decodedImage = BitmapPainter(
                            factory.asImageBitmap()
                        )
                        decodedPhotos.add(decodedImage)
                    }
                }
            }

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

        }
        return Establishment(
            establishment.id,
            establishment.name,
            establishment.description,
            establishment.address,
            User("Олег"),
            establishment.hasCardPayment,
            establishment.hasMap,
            establishment.map,
            establishment.category,
            decodedImage,
            establishment.rating,
            establishment.price,
            establishment.workingHours,
            decodedTagsIcons,
            decodedPhotos,
            establishment.cuisineCountry,
            establishment.starsCount
        )
    }
}
