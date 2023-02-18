package fit.budle.ui.screens

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import fit.budle.model.Establishment
import fit.budle.model.EstablishmentResult
import fit.budle.model.EstablishmentStructure
import fit.budle.model.EstablishmentsRequest
import fit.budle.ui.details.InstitutionsRow

@Composable
fun HomeScreen(
    navController: NavController, establishmentProvider: (
        category: String?, limit: Int?, offset: Int?, sortValue: String?, name: String?, hasCardPayment: Boolean, hasMap: Boolean
    ) -> (EstablishmentStructure)
) {
    val establishmentRequest: EstablishmentsRequest =
        remember { EstablishmentsRequest(null, null, null, null, null, false, false) }
    InstitutionsRow(
        establishments = establishmentProvider(
            establishmentRequest.category,
            establishmentRequest.limit,
            establishmentRequest.offset,
            establishmentRequest.sortValue,
            establishmentRequest.name,
            establishmentRequest.hasCardPayment,
            establishmentRequest.hasMap
        )
    )
}
