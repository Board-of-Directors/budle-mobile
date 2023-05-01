package fit.budle.dto.events

import android.graphics.Bitmap
import fit.budle.dto.WorkingHour

sealed interface EstCreationEvent {
    data class FirstStep(val imageBitmap: Bitmap, val name: String) : EstCreationEvent
    data class SecondStep(val category: String, val tags: List<String>) : EstCreationEvent
    data class ThirdStep(val description: String, val photosInput: List<Bitmap>) : EstCreationEvent
    data class FourthStep(val address: String, val workingHours: List<WorkingHour>) : EstCreationEvent
    object CreateEstablishment : EstCreationEvent
    object GetCategoryListEvent: EstCreationEvent
    object GetTagListEvent : EstCreationEvent
    data class GetVariantList(val category: String) : EstCreationEvent
}