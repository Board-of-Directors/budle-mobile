package fit.budle.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import fit.budle.model.EstablishmentStructure
import fit.budle.model.EstablishmentRequest
import fit.budle.ui.details.InstitutionsRow

@Composable
fun HomeScreen(
    navController: NavController, establishmentProvider: (
        category: String?, limit: Int?, offset: Int?, sortValue: String?, name: String?, hasCardPayment: Boolean?, hasMap: Boolean?
    ) -> (EstablishmentStructure), categoriesProvider: () -> (Array<String>)
) {
    val establishmentRequest =
        EstablishmentRequest(null, null, null, null, null, null, null)

    val columnState = rememberLazyListState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = columnState,
            horizontalAlignment = Alignment.Start
        ) {
            itemsIndexed(categoriesProvider()) { _, i ->
                InstitutionsRow(
                    establishmentProvider(
                        i,
                        establishmentRequest.limit,
                        establishmentRequest.offset,
                        establishmentRequest.sortValue,
                        establishmentRequest.name,
                        establishmentRequest.hasCardPayment,
                        establishmentRequest.hasMap
                    ),
                    navController
                )
            }
        }
    }
}
