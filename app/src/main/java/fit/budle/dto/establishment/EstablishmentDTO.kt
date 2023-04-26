package fit.budle.dto.establishment

import androidx.compose.ui.graphics.painter.BitmapPainter
import fit.budle.dto.WorkingHour
import fit.budle.dto.customer_user.UserDTO
import fit.budle.dto.tag.standard.Tag

data class EstablishmentDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val address: String,
    val owner: UserDTO,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean?,
    val category: String,
    val image: BitmapPainter?,
    val rating: Double?,
    val price: Int?,
    val workingHours: Array<WorkingHour>,
    val tags: ArrayList<Tag>,
    val cuisineCountry: String?,
    val starsCount: Int?,
)
