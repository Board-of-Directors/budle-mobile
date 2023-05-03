package fit.budle.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.WorkingHour
import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.dto.establishment.ReturnTag
import fit.budle.dto.establishment.establishment_field.PhotoDto
import fit.budle.dto.establishment.etsablishment_type.EstablishmentExtendedDto
import fit.budle.dto.events.EstCreationEvent
import fit.budle.dto.result.GetCategoryListResult
import fit.budle.dto.result.GetCategoryVariantListResult
import fit.budle.dto.result.GetTagListResult
import fit.budle.dto.tag.standard.TagResponse
import fit.budle.repository.EstCreationRepository
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class EstCreationViewModel @Inject constructor(
    private val estCreationRepository: EstCreationRepository,
) : ViewModel() {

    var establishmentDTO by mutableStateOf(EstablishmentDTO())

    val testCategoryMap = mapOf(
        "Рестораны" to "cuisineCountry",
        "Отели" to "starsCount"
    )

    var selectedMainImageUri by mutableStateOf<Uri?>(null)
    var selectedPhotosUri by mutableStateOf(emptyList<Uri>())

    var categoryList by mutableStateOf(emptyList<String>())
    var tagList by mutableStateOf(emptyList<TagResponse>())
    var variantList by mutableStateOf(emptyList<String>())

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
                viewModelScope.launch {
                    val tagList = mutableListOf<ReturnTag>()
                    for (tag in event.tags) {
                        tagList.add(ReturnTag(tag))
                    }
                    establishmentDTO.category = event.category
                    establishmentDTO.tags = tagList
                    Log.d("IMAGE", establishmentDTO.image)
                    Log.d("NAME", establishmentDTO.name)
                    Log.d("CATEGORY", establishmentDTO.category!!)
                    Log.d("TAGS", establishmentDTO.tags.size.toString())
                }
            }
            is EstCreationEvent.ThirdStep -> {
                viewModelScope.launch {
                    establishmentDTO.description = event.description
                    val encodedBitmaps = mutableListOf<PhotoDto>()
                    event.photosInput.forEach { bitmap ->
                        encodedBitmaps.add(PhotoDto(convertBitmapToBase64(bitmap)))
                    }
                    establishmentDTO.photosInput = encodedBitmaps.toList()
                    Log.d("IMAGE", establishmentDTO.image)
                    Log.d("NAME", establishmentDTO.name)
                    Log.d("CATEGORY", establishmentDTO.category!!)
                    Log.d("TAGS", establishmentDTO.tags.size.toString())
                    Log.d("DESCRP", establishmentDTO.description!!)
                    Log.d("PHOTOS", establishmentDTO.photosInput.size.toString())
                }
            }
            is EstCreationEvent.FourthStep -> {
                viewModelScope.launch {
                    establishmentDTO.address = event.address
                    establishmentDTO.workingHours = listOf(WorkingHour("Пн", "12:00", "22:00"))
                    Log.d("IMAGE", establishmentDTO.image)
                    Log.d("NAME", establishmentDTO.name)
                    Log.d("CATEGORY", establishmentDTO.category!!)
                    Log.d("TAGS", establishmentDTO.tags.size.toString())
                    Log.d("DESCRP", establishmentDTO.description!!)
                    Log.d("PHOTOS", establishmentDTO.photosInput.size.toString())
                    Log.d("ADDRESS", establishmentDTO.address)
                    Log.d("WORKINGHOURS", establishmentDTO.workingHours.size.toString())
                }
            }
            is EstCreationEvent.CreateEstablishment -> {
                viewModelScope.launch {
                    estCreationRepository.postEstablishment(establishmentDTO)
                }
            }
            is EstCreationEvent.GetCategoryListEvent -> {
                viewModelScope.launch {
                    when (val result = estCreationRepository.getCategoryList()) {
                        is GetCategoryListResult.Success -> {
                            Log.d("VM_GET_CATEGORY_LIST", "SUCCESS")
                            categoryList = result.result
                        }
                        else -> {
                            Log.d("VM_GET_CATEGORY_LIST", "FAILURE")
                        }
                    }
                }
            }
            is EstCreationEvent.GetTagListEvent -> {
                viewModelScope.launch {
                    when (val result = estCreationRepository.getTagList()) {
                        is GetTagListResult.Success -> {

                            Log.d("VM_GET_TAG_LIST", "SUCCESS")
                            val list = mutableListOf<TagResponse>()

                            result.result.forEach {
                                val svgString = convertBase64toSVG(it.image)
                                Log.d("SVG", svgString!!)
                                list.add(
                                    TagResponse(
                                        it.name,
                                        svgString
                                    )
                                )
                            }

                            tagList = list
                        }
                        else -> {
                            Log.d("VM_GET_CATEGORY_LIST", "FAILURE")
                        }
                    }
                }
            }
            is EstCreationEvent.GetVariantList -> {
                viewModelScope.launch {
                    when (val result =
                        estCreationRepository.getCategoryVariantList(event.category)) {
                        is GetCategoryVariantListResult.Success -> {
                            variantList = result.result
                        }
                        else -> {
                            Log.d("VM_GET_VARIANT_LIST", "FAILURE")
                        }
                    }
                }
            }
        }
    }

    private fun convertBase64toImageBitmap(base64: String?): BitmapPainter? {
        return if (base64 != null) {
            val imageBytes: ByteArray = Base64.decode(base64, Base64.DEFAULT)
            BitmapPainter(
                BitmapFactory
                    .decodeByteArray(imageBytes, 0, imageBytes.size)
                    .asImageBitmap()
            )
        } else return null
    }

    private fun convertBase64toSVG(base64: String?): String? {
        return if (base64 != null) {
            String(Base64.decode(base64, Base64.DEFAULT))
        } else return null
    }

    private fun convertBitmapToBase64(imageBitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val byteArray = baos.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT).replace("\n", "")
    }
}