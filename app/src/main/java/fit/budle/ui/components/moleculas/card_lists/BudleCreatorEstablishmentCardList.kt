package fit.budle.ui.components.moleculas.card_lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.ui.components.moleculas.BudleCreatorEstablishmentCard

@Composable
fun BudleCreatorEstablishmentCardList(
    navHostController: NavHostController,
    cards: List<EstablishmentShortDto>,
    modifier: Modifier = Modifier,
    onDelete: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        for(card in cards){
            BudleCreatorEstablishmentCard(
                navHostController = navHostController,
                establishmentCard = card,
                onDelete = {
                    onDelete(it)
                }
            )
        }
    }
}