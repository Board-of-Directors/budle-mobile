package fit.budle.dto.establishment

import androidx.compose.ui.graphics.painter.BitmapPainter
import fit.budle.dto.WorkingHour
import fit.budle.dto.customer_user.User
import fit.budle.dto.tag.standard.Tag

data class EstablishmentArray(
    val establishments: Array<Establishment> = emptyArray(),
    val count: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EstablishmentArray

        if (!establishments[0].category.equals(other.establishments[0].category)) return false
        if (count != other.count) return false

        return true
    }

    override fun hashCode(): Int {
        var result = establishments.contentHashCode()
        result = 31 * result + count
        return result
    }
}

data class Establishment(
    val id: Long = 0,
    val name: String = "",
    val description: String? = "",
    val address: String = "",
    val owner: User = User(""),
    val hasCardPayment: Boolean? = false,
    val hasMap: Boolean = false,
    val category: String = "",
    val image: BitmapPainter? = null,
    val rating: Double? = null,
    val price: Int? = 1,
    val workingHours: Array<WorkingHour>? = null,
    val tags: ArrayList<Tag> = arrayListOf(),
    val cuisineCountry: String? = "",
    val starsCount: Int? = 1,
)
