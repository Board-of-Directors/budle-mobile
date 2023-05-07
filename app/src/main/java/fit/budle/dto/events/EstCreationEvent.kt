package fit.budle.dto.events

sealed interface EstCreationEvent {
    object FirstStep : EstCreationEvent
    object SecondStep : EstCreationEvent
    object ThirdStep : EstCreationEvent
    object FourthStep : EstCreationEvent
    object CreateEstablishment : EstCreationEvent
    object GetCategoryListEvent: EstCreationEvent
    object GetTagListEvent : EstCreationEvent
    object GetVariantList : EstCreationEvent
}