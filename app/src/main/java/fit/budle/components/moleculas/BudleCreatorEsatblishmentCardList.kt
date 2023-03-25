package fit.budle.components.moleculas

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fit.budle.models.BusinessEstablishmentModel

@Composable
fun BudleCreatorEstablishmentCardList(
    navHostController: NavHostController,
    cards: MutableList<BusinessEstablishmentModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        itemsIndexed(cards) { _, card ->
            BudleCreatorEstablishmentCard(
                navHostController = navHostController,
                establishmentCard = card
            )
        }
    }
}