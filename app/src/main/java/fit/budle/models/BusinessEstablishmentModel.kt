package fit.budle.models

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BusinessEstablishmentModel(
    val establishment: EstablishmentCard,
    val workers: MutableList<Worker>,
    val orders: MutableList<Booking>,
    val isOpened: @RawValue MutableState<Boolean> = mutableStateOf(false)
) : Parcelable

@Parcelize
data class Worker(
    val name: String,
    val status: WorkerStatus
) : Parcelable

enum class WorkerStatus(val message: String) {
    WORK("На работе"), ABSENT("Отсутствует")
}

val workerFirstList = mutableListOf(
    Worker(
        "Вебер Олег",
        WorkerStatus.ABSENT
    ),
    Worker(
        "Катешов Илья",
        WorkerStatus.WORK
    ),
    Worker(
        "Константинов Никита",
        WorkerStatus.WORK
    ),
)

val workerSecondList = mutableListOf(
    Worker(
        "Асташова Татьяна",
        WorkerStatus.ABSENT
    ),
    Worker(
        "Тюпина Елизавета",
        WorkerStatus.ABSENT
    ),
    Worker(
        "Ивахник Вадим",
        WorkerStatus.ABSENT
    ),
)

val businessEstablishmentList = mutableListOf(
    BusinessEstablishmentModel(
        restaurants[0],
        workerFirstList,
        bookingList
    ),
    BusinessEstablishmentModel(
        restaurants[1],
        workerSecondList,
        bookingList
    ),
)