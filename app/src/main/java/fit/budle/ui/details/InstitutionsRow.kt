package fit.budle.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.model.EstablishmentStructure
import fit.budle.model.EstablishmentsRequest
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun InstitutionsRow(
    establishments: EstablishmentStructure
) {
    val rowState = rememberLazyListState()
    if (establishments.establishments.isNotEmpty()) {
        Column(Modifier.padding(top = 20.dp)) {
            Row(Modifier.padding(start = 20.dp)) {
                Text(
                    text = if (establishments.establishments.isNotEmpty()) establishments.establishments[0].category else "Ничего",
                    style = MaterialTheme.typography.h3,
                    color = mainBlack
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = establishments.count.toString(),
                    style = MaterialTheme.typography.h3,
                    color = textGray,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
            LazyRow(
                state = rowState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(establishments.establishments) { i, _ ->
                    if (establishments.establishments.isNotEmpty()) {
                        InstitutionCard(i = i, institutionCardState = establishments.establishments)
                    }
                }
            }
        }
    }

}
