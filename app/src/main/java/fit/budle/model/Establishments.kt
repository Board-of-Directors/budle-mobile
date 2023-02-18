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
    val hasCardPayment: Boolean,
    val hasMap: Boolean,
    val category: String,
    val image: String?,
    val rating: String?,
    val price: String?
)

data class Institutions(
    val type: String, val amount: String, val institutionsList: SnapshotStateList<InstitutionCard>
)

data class InstitutionCard(
    val type: String,
    val name: String,
    val imgID: Int,
    val rate: Double,
)

val restaurants = mutableStateListOf(
    InstitutionCard(
        "Ресторан", "Горячий цех", R.drawable.institutions_hotels_1, 4.7
    ),
    InstitutionCard(
        "Ресторан", "Мама, я дома!", R.drawable.institutions_hotels_1, 4.8
    ),
    InstitutionCard(
        "Ресторан", "Аджикинежаль", R.drawable.institutions_hotels_1, 4.8
    ),
    InstitutionCard(
        "Ресторан", "Дядя Дёнер", R.drawable.institutions_hotels_1, 3.7
    ),
)

val hotels = mutableStateListOf(
    InstitutionCard(
        "Гостиница", "Mariott", R.drawable.institutions_hotels_1, 3.2
    ),
    InstitutionCard(
        "Гостиница", "River Plaza", R.drawable.institutions_hotels_1, 4.7
    ),
    InstitutionCard(
        "Гостиница", "Mirotel", R.drawable.institutions_hotels_1, 4.8
    ),
    InstitutionCard(
        "Гостиница", "Double Tree", R.drawable.institutions_hotels_1, 3.3
    ),
)

val institutions = mutableStateListOf(
    Institutions(
        "Рестораны", "Все 182", restaurants
    ),
    Institutions(
        "Гостиницы", "Все 381", hotels
    ),
)
