package fit.budle.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fit.budle.model.EstablishmentResult
import fit.budle.model.EstablishmentStructure
import fit.budle.model.InstitutionCard
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun InstitutionCard(i: Int, institutionCardState: EstablishmentStructure) {
    //val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Card(
        modifier = Modifier.padding(start = 10.dp), shape = RoundedCornerShape(15.dp)
    ) {
        Image(
            painter = institutionCardState.establishments[i].image!!,
            contentDescription = "Restaurant Card",
            modifier = Modifier.width(140.dp),
            contentScale = ContentScale.Crop
        )
        Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.Bottom) {
            Text(
                text = institutionCardState.establishments[i].name,
                style = MaterialTheme.typography.body1,
                color = mainWhite,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Card(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .width(38.dp)
                    .height(20.dp),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = institutionCardState.establishments[i].rating.toString(),
                    style = MaterialTheme.typography.body1,
                    color = mainBlack,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
