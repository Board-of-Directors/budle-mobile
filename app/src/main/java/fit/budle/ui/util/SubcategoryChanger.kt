package fit.budle.ui.util

import fit.budle.dto.establishment.etsablishment_type.NewEstablishmentDto
import fit.budle.viewmodel.business.EstCreationViewModel
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.typeOf

class SubcategoryChanger {
    companion object {
        fun SetSubcategory(
            viewModel: EstCreationViewModel,
            name: String,
            result: String,
        ) {
            val member = NewEstablishmentDto::class.declaredMemberProperties.find {
                it.name == name
            } as KMutableProperty<*>

            when (member.returnType) {
                typeOf<Int?>() -> {
                    member.setter.call(viewModel.establishmentDTO, result.toIntOrNull())
                }
                else -> {
                    member.setter.call(viewModel.establishmentDTO, result)
                }
            }
        }

        fun GetSubcategoryValue(
            viewModel: EstCreationViewModel,
            name: String,
        ): String {
            val property = NewEstablishmentDto::class.declaredMemberProperties.find {
                it.name == name
            } as KMutableProperty<*>
            return property.getter.call(viewModel.establishmentDTO).toString()
        }
    }
}