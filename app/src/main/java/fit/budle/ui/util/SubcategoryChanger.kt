package fit.budle.ui.util

import fit.budle.dto.establishment.EstablishmentDTO
import fit.budle.viewmodel.EstCreationViewModel
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
            val member = EstablishmentDTO::class.declaredMemberProperties.find {
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
            val property = EstablishmentDTO::class.declaredMemberProperties.find {
                it.name == name
            } as KMutableProperty<*>
            return property.getter.call(viewModel.establishmentDTO).toString()
        }
    }
}