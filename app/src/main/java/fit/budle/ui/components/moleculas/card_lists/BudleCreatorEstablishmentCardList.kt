package fit.budle.ui.components.moleculas.card_lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.establishment.EstablishmentResponse
import fit.budle.ui.components.moleculas.BudleCreatorEstablishmentCard

@Composable
fun BudleCreatorEstablishmentCardList(
    navHostController: NavHostController,
    cards: List<EstablishmentResponse>,
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