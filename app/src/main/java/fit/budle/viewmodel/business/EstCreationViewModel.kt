package fit.budle.viewmodel.business

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.di.establishment.WorkingHoursDto
import fit.budle.di.establishment.establishment_field.PhotoDto
import fit.budle.di.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.WorkingHour
import fit.budle.dto.events.EstCreationEvent
import fit.budle.dto.result.GetCategoryListResult
import fit.budle.dto.result.GetCategoryVariantListResult
import fit.budle.dto.result.GetTagListResult
import fit.budle.dto.tag.ReturnTag
import fit.budle.dto.tag.standard.TagResponse
import fit.budle.repository.business.EstCreationRepository
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class EstCreationViewModel @Inject constructor(
    private val estCreationRepository: EstCreationRepository,
) : ViewModel() {

    var establishmentDTO by mutableStateOf(NewEstablishmentDto())

    // required fields
    var selectedName by mutableStateOf("")
    var selectedImageUri by mutableStateOf<Uri?>(null)
    var selectedImageBitmap by mutableStateOf<Bitmap?>(null)
    var selectedCategory by mutableStateOf("")
    var selectedTagNames = mutableStateListOf<String>()
    var selectedDescription by mutableStateOf("")
    var selectedPhotosBitmap by mutableStateOf(emptyList<Bitmap>())
    var selectedPhotosUri = mutableListOf<Uri>()
    var selectedAddress by mutableStateOf("")
    var selectedWorkingHours = mutableStateMapOf<Int, WorkingHoursDto>()
    var hasMap by mutableStateOf(false)
    var blocksCount by mutableStateOf(1)

    // optional fields
    var selectedCuisineCountry by mutableStateOf<String?>(null)
    var selectedStarsCount by mutableStateOf<String?>(null)

    val testCategoryMap = mapOf(
        "Рестораны" to "cuisineCountry",
        "Отели" to "starsCount"
    )

    var categoryList by mutableStateOf(emptyList<String>())
    var tagList by mutableStateOf(emptyList<TagResponse>())
    var variantList by mutableStateOf(emptyList<String>())

    fun onEvent(event: EstCreationEvent) {
        when (event) {
            is EstCreationEvent.FirstStep -> {
                viewModelScope.launch {
                    val convertedImage = convertBitmapToBase64(selectedImageBitmap)
                    if (convertedImage != null) {
                        establishmentDTO.image = convertedImage
                        establishmentDTO.name = selectedName
                    } else {
                        Log.d("FIRST_STEP", "IMAGE IS NULL")
                    }
                }
            }
            is EstCreationEvent.SecondStep -> {
                viewModelScope.launch {
                    establishmentDTO.category = selectedCategory
                    val selectedTags = mutableListOf<ReturnTag>()
                    selectedTagNames.forEach { selectedTags.add(ReturnTag(it)) }
                    establishmentDTO.tags = selectedTags
                }
            }
            is EstCreationEvent.ThirdStep -> {
                viewModelScope.launch {
                    establishmentDTO.description = selectedDescription
                    val convertedPhotos = mutableListOf<PhotoDto>()
                    selectedPhotosBitmap.forEach { bitmap ->
                        val convertedPhoto = convertBitmapToBase64(bitmap)
                        if (convertedPhoto != null) {
                            convertedPhotos.add(PhotoDto(convertedPhoto))
                        } else Log.d("THIRD_STEP", "IMAGE IS NULL")
                    }
                    establishmentDTO.photosInput = convertedPhotos
                }
            }
            is EstCreationEvent.FourthStep -> {
                viewModelScope.launch {
                    establishmentDTO.address = selectedAddress
                    establishmentDTO.workingHours = listOf(WorkingHour("Пн", "12:00", "22:00"))
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
                        estCreationRepository.getCategoryVariantList(selectedCategory)) {
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

    private fun convertBitmapToBase64(imageBitmap: Bitmap?): String? {
        return if (imageBitmap != null) {
            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val byteArray = baos.toByteArray()
            Base64.encodeToString(byteArray, Base64.DEFAULT).replace("\n", "")
        } else return null
    }
}