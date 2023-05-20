package fit.budle.di.establishment

import androidx.compose.ui.graphics.painter.BitmapPainter
import fit.budle.dto.WorkingHour
import fit.budle.dto.customer_user.User
import fit.budle.dto.tag.standard.Tag

data class EstablishmentArray(val establishments: Array<Establishment> = emptyArray(), val count: Int = 0)

data class Establishment(
    val id: Long = 0,
    val name: String = "",
    val description: String? = "",
    val address: String = "",
    val owner: User = User(""),
    val hasCardPayment: Boolean? = false,
    val hasMap: Boolean? = false,
    val category: String = "",
    val image: BitmapPainter? = null,
    val rating: Double? = null,
    val price: Int? = 1,
    val workingHours: Array<WorkingHour>? = null,
    val tags: ArrayList<Tag> = arrayListOf(),
    val cuisineCountry: String? = "",
    val starsCount: Int? = 1
)
