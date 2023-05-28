package fit.budle.event.business

import android.content.ContentResolver
import android.graphics.ImageDecoder

sealed interface EstCreationEvent {
    data class FirstStep(val source: ImageDecoder.Source?) : EstCreationEvent
    object SecondStep : EstCreationEvent
    data class ThirdStep(val resolver: ContentResolver) : EstCreationEvent
    object FourthStep : EstCreationEvent
    object CreateMap : EstCreationEvent
    object CreateEstablishment : EstCreationEvent
    object GetCategoryListEvent : EstCreationEvent
    object GetTagListEvent : EstCreationEvent
    object GetVariantList : EstCreationEvent
}