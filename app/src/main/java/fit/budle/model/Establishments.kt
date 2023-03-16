package fit.budle.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import fit.budle.R

data class Establishment(
    val id: Int,
    val name: String,
    val description: String?,
    val address: String,
    val owner: Owner,
    val hasCardPayment: Boolean?,
    val hasMap: Boolean?,
    val category: String,
    val image: String?,
    val rating: Int?,
    val price: Int?
)
