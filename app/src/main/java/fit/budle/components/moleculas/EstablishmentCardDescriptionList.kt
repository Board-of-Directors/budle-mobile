package fit.budle.components.moleculas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.BudleEstablishmentCardDescription
import fit.budle.models.EstablishmentDescription

@Composable
fun BudleEstablishmentCardDescriptionList(
    establishmentDescription: MutableList<EstablishmentDescription>,
    filter: String = "Все"
) {
    val listState = remember { establishmentDescription }
    LazyColumn(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxSize()
    ) {
        itemsIndexed(listState) { _, establishment ->
            if (filter == "Все") {
                BudleEstablishmentCardDescription(
                    establishmentDescription = establishment
                )
            } else if (establishment.type.startsWith(
                    filter.substring(0, filter.length - 2), true
                )) {
                BudleEstablishmentCardDescription(
                    establishmentDescription = establishment
                )
            }
        }
    }
}