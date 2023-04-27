package fit.budle.viewmodel

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.dto.events.EstCreationEvent
import fit.budle.repository.EstCreationRepository
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@HiltViewModel
class EstCreationViewModel @Inject constructor(
    private val estCreationRepository: EstCreationRepository,
) : ViewModel() {

    var establishmentDTO: EstablishmentDTO = EstablishmentDTO()

    fun onEvent(event: EstCreationEvent) {
        when (event) {
            is EstCreationEvent.FirstStep -> {
                viewModelScope.launch {
                    establishmentDTO.image = convertBitmapToBase64(event.imageBitmap)
                    establishmentDTO.name = event.name
                    Log.d("IMAGE", establishmentDTO.image)
                    Log.d("NAME", establishmentDTO.name)
                }
            }
            is EstCreationEvent.SecondStep -> {
                establishmentDTO.category = event.category
                establishmentDTO.tags = event.tags
            }
            is EstCreationEvent.ThirdStep -> {
                establishmentDTO.description = event.description
                establishmentDTO.photosInput = event.photosInput
            }
            is EstCreationEvent.FourthStep -> {
                establishmentDTO.address = event.address
                establishmentDTO.workingHours = event.workingHours
            }
            is EstCreationEvent.CreateEstablishment -> {
                viewModelScope.launch {
                    estCreationRepository.postEstablishment(establishmentDTO)
                }
            }
        }
    }

    private fun convertBitmapToBase64(imageBitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArray = baos.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}