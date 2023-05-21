package fit.budle.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fit.budle.dto.establishment.EstablishmentArray
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleInstitutionsRow(
    establishments: EstablishmentArray,
    navController: NavController
) {
    val rowState = rememberLazyListState()
    if (establishments.establishments.isNotEmpty()) {
        Column(Modifier.padding(top = 20.dp)) {
            Row(Modifier.padding(start = 20.dp)) {
                Text(
                    text = if (establishments.establishments.isNotEmpty()) establishments.establishments[0].category else "Ничего",
                    style = MaterialTheme.typography.titleSmall,
                    color = mainBlack
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = establishments.count.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    color = textGray,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
            LazyRow(
                state = rowState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                itemsIndexed(establishments.establishments) { i, _ ->
                    if (establishments.establishments.isNotEmpty()) {
                        /*BudleInstitutionCard(
                            i = i,
                            institutionCardState = establishments.establishments,
                            navController,
                            category = establishments.establishments[i].category
                        ) */
                    }
                }
            }
        }
    }
}
