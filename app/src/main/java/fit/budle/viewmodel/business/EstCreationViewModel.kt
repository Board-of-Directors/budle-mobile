package fit.budle.viewmodel.business

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.WorkingHour
import fit.budle.dto.establishment.WorkingHoursDto
import fit.budle.dto.establishment.establishment_field.PhotoDto
import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.dto.result.GetCategoryListResult
import fit.budle.dto.result.GetCategoryVariantListResult
import fit.budle.dto.result.GetTagListResult
import fit.budle.dto.tag.ReturnTag
import fit.budle.dto.tag.standard.TagResponse
import fit.budle.event.business.EstCreationEvent
import fit.budle.repository.business.EstCreationRepository
import fit.budle.ui.util.bitmapCreator
import fit.budle.util.FileEncoder
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EstCreationViewModel @Inject constructor(
    private val estCreationRepository: EstCreationRepository,
) : ViewModel() {

    val fileEncoder = FileEncoder()
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

    var selectedMapUri by mutableStateOf<Uri?>(null)
    var selectedMapFile by mutableStateOf<File?>(null)

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

    @RequiresApi(Build.VERSION_CODES.P)
    fun onEvent(event: EstCreationEvent) {
        when (event) {
            is EstCreationEvent.FirstStep -> {
                viewModelScope.launch {
                    val convertedImage = fileEncoder.encodeBitmapToBase64(selectedImageBitmap)
                    selectedImageBitmap = event.source?.let { ImageDecoder.decodeBitmap(it) }
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
                    selectedPhotosBitmap = bitmapCreator(
                        uris = selectedPhotosUri.toTypedArray(),
                        event.resolver
                    )
                    establishmentDTO.description = selectedDescription
                    val convertedPhotos = mutableListOf<PhotoDto>()
                    selectedPhotosBitmap.forEach { bitmap ->
                        val convertedPhoto = fileEncoder.encodeBitmapToBase64(bitmap)
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
                    establishmentDTO.workingHours = listOf(
                        WorkingHour(
                            listOf("Пн"),
                            "12:00",
                            "22:00"
                        )
                    )
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
                            //FIXME: null image
                            result.result.forEach {
                                list.add(
                                    TagResponse(
                                        it.name,
                                        null
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

            is EstCreationEvent.CreateMap -> {
                viewModelScope.launch {
                    if (selectedMapFile != null) {
                        val svg = selectedMapFile!!.readText(Charsets.UTF_8)
                        val encodedMap = fileEncoder.encodeStringToBase64(svg)
                        if (encodedMap != null) {
                            establishmentDTO.map = svg
                        } else Log.e("MAP", "Cannot encode map to base64")
                        Log.i("MAP", encodedMap.toString())
                    }
                }
            }
        }
    }
}