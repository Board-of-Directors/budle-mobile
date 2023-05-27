package fit.budle.event.business

sealed interface EstCreationEvent {
    object FirstStep : EstCreationEvent
    object SecondStep : EstCreationEvent
    object ThirdStep : EstCreationEvent
    object FourthStep : EstCreationEvent
    object CreateMap : EstCreationEvent
    object CreateEstablishment : EstCreationEvent
    object GetCategoryListEvent : EstCreationEvent
    object GetTagListEvent : EstCreationEvent
    object GetVariantList : EstCreationEvent
}