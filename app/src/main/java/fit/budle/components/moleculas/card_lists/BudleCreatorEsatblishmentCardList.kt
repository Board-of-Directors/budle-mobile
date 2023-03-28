package fit.budle.components.moleculas

import androidx.compose.foundation.layout.Column
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
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        for(card in cards){
            BudleCreatorEstablishmentCard(
                navHostController = navHostController,
                establishmentCard = card
            )
        }
    }
}