package fit.budle.model

import androidx.compose.ui.graphics.painter.BitmapPainter

data class EstablishmentStructure (val establishments: Array<EstablishmentWithImage>, val count: Int)

data class EstablishmentWithImage(
    val id: Int,
    val name: String,
    val description: String?,
    val address: String,
    val owner: Owner,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean?,
    val category: String,
    val image: BitmapPainter?,
    val rating: Double?,
    val price: Int?
)
