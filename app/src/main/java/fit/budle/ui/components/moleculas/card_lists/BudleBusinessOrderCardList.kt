package fit.budle.ui.components.moleculas.card_lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fit.budle.dto.order.BusinessOrderDto
import fit.budle.ui.components.atoms.cards.BudleBusinessOrderCard
import fit.budle.viewmodel.business.EstOrderListViewModel

@Composable
fun BudleBusinessOrderCardList(
    modifier: Modifier = Modifier,
    bookingList: List<BusinessOrderDto>,
    viewModel: EstOrderListViewModel
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        for (booking in bookingList) {
            BudleBusinessOrderCard(
                booking = booking,
                viewModel = viewModel
            )
        }
    }
}