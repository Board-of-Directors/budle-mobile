package fit.budle.ui.screens.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.ui.components.atoms.cards.BudleEstablishmentCardDescription
import fit.budle.ui.components.moleculas.BudleSearchBar
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.MainViewModel

@Composable
fun EstablishmentSearchScreen(
    navHostController: NavHostController,
    viewModel: MainViewModel,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        BudleSearchBar(
            navHostController = navHostController,
            viewModel = viewModel
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Найдено ${viewModel.filteredEstablishmentList.size} заведений",
                style = MaterialTheme.typography.titleSmall,
                color = mainBlack
            )
            Row(
                modifier = Modifier.clickable {
                    viewModel.isFiltersVisible = false
                    navHostController.popBackStack()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "back",
                    tint = textGray
                )
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = "Назад",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(viewModel.filteredEstablishmentList) { _, card ->
                BudleEstablishmentCardDescription(establishment = card)
            }
        }
    }
}