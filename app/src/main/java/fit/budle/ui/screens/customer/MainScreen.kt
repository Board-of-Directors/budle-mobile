package fit.budle.ui.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fit.budle.event.customer.MainEvent
import fit.budle.ui.components.moleculas.BudleSearchBar
import fit.budle.ui.components.moleculas.establishments.EstablishmentRow
import fit.budle.ui.components.organism.BudleFilterPopup
import fit.budle.viewmodel.customer.MainViewModel

@Composable
fun MainScreen(
    navHostController: NavController,
    viewModel: MainViewModel,
) {
    viewModel.onEvent(MainEvent.GetAllEstablishments)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (viewModel.isFiltersVisible) {
            BudleFilterPopup(
                navHostController = navHostController,
                viewModel = viewModel,
                onClose = {
                    viewModel.isFiltersVisible = !viewModel.isFiltersVisible
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            BudleSearchBar(
                navHostController = navHostController,
                onValueChange = {
                    viewModel.selectedEstablishmentName = it
                    navHostController.navigate("searchScreen")
                },
                viewModel = viewModel
            )
            LazyColumn(
                horizontalAlignment = Alignment.Start
            ) {
                itemsIndexed(viewModel.establishmentsForScreen) { _, establishment ->
                    EstablishmentRow(
                        establishment,
                        navHostController,
                        viewModel
                    )
                }

            }
        }
    }
}
