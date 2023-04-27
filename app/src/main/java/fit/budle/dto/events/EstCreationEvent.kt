package fit.budle.dto.events

import android.graphics.Bitmap
import fit.budle.dto.WorkingHour
import fit.budle.dto.establishment.ImageDTO
import fit.budle.dto.tag.standard.TagResponse

sealed interface EstCreationEvent {
    data class FirstStep(val imageBitmap: Bitmap, val name: String) : EstCreationEvent
    data class SecondStep(val category: String, val tags: List<TagResponse>) : EstCreationEvent
    data class ThirdStep(val description: String, val photosInput: List<ImageDTO>) : EstCreationEvent
    data class FourthStep(val address: String, val workingHours: List<WorkingHour>) : EstCreationEvent
    object CreateEstablishment : EstCreationEvent
}