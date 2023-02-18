package fit.budle.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.model.Establishment
import fit.budle.model.EstablishmentResult
import fit.budle.model.EstablishmentStructure
import fit.budle.model.Institutions
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray

@Composable
fun InstitutionsRow(establishments: EstablishmentStructure) {

    val institutionCardState = remember { establishments }
    val rowState = rememberLazyListState()

    Column(Modifier.padding(top = 20.dp)) {
        Row(Modifier.padding(start = 20.dp)) {
            Text(
                text = establishments.establishments[0].category, style = MaterialTheme.typography.h3, color = mainBlack
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = establishments.amount.toString(),
                style = MaterialTheme.typography.h3,
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
            itemsIndexed(institutionCardState.establishments) { i, _ ->
                InstitutionCard(i = i, institutionCardState = institutionCardState)
            }
        }
    }
}
