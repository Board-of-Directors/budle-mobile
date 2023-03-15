package nsu.app.budle.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.budle.R

data class Institutions(
    val type: String,
    val amount: String,
    val institutionsList: SnapshotStateList<InstitutionCard>
)

data class InstitutionCard(
    val type: String,
    val name: String,
    val imgID: Int,
    val rate: Double,
)

val restaurants = mutableStateListOf(
    InstitutionCard(
        "Ресторан",
        "Горячий цех",
        R.drawable.institutions_restaurants_1,
        4.7
    ),
    InstitutionCard(
        "Ресторан",
        "Мама, я дома!",
        R.drawable.institutions_restaurants_2,
        4.8
    ),
    InstitutionCard(
        "Ресторан",
        "Аджикинежаль",
        R.drawable.institutions_restaurants_3,
        4.8
    ),
    InstitutionCard(
        "Ресторан",
        "Дядя Дёнер",
        R.drawable.institutions_restaurants_2,
        3.7
    ),
)

val hotels = mutableStateListOf(
    InstitutionCard(
        "Гостиница",
        "Mariott",
        R.drawable.institutions_hotels_1,
        3.2
    ),
    InstitutionCard(
        "Гостиница",
        "River Plaza",
        R.drawable.institutions_hotels_2,
        4.7
    ),
    InstitutionCard(
        "Гостиница",
        "Mirotel",
        R.drawable.institutions_hotels_3,
        4.8
    ),
    InstitutionCard(
        "Гостиница",
        "Double Tree",
        R.drawable.institutions_hotels_2,
        3.3
    ),
)

val institutions = mutableStateListOf(
    Institutions(
        "Рестораны",
        "Все 182",
        restaurants
    ),
    Institutions(
        "Гостиницы",
        "Все 381",
        hotels
    ),
)