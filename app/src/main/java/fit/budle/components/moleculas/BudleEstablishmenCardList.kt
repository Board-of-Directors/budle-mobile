package fit.budle.components.moleculas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.BudleEstablishmentCard
import fit.budle.models.Establishments
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleEstablishmentList(establishments: Establishments) {

    val establishmentCardState = remember { establishments.establishmentList }
    val rowState = rememberLazyListState()

    Column(Modifier.padding(top = 20.dp)) {
        // current block of institutions
        Row(Modifier.padding(start = 20.dp)) {
            Text(
                text = establishments.type,
                style = MaterialTheme.typography.titleSmall,
                color = mainBlack
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = establishments.amount,
                style = MaterialTheme.typography.titleSmall,
                color = textGray,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
        LazyRow(
            state = rowState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(establishmentCardState) { i, _ ->
                BudleEstablishmentCard(establishmentCard = establishmentCardState[i])
            }
        }
    }
}