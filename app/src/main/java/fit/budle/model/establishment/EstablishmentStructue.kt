package fit.budle.model.establishment

import androidx.compose.ui.graphics.painter.BitmapPainter
import fit.budle.model.Owner
import fit.budle.model.tag.standard.Tag
import fit.budle.model.WorkingHour

data class EstablishmentArray(val establishments: Array<Establishment>, val count: Int)

data class Establishment(
    val id: Long,
    val name: String,
    val description: String?,
    val address: String,
    val owner: Owner,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean?,
    val category: String,
    val image: BitmapPainter?,
    val rating: Double?,
    val price: Int?,
    val workingHours: Array<WorkingHour>,
    val tags: ArrayList<Tag>,
    val cuisineCountry: String?,
    val starsCount: Int?
)
