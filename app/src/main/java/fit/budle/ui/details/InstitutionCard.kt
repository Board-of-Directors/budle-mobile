package fit.budle.ui.details

import androidx.annotation.Dimension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fit.budle.model.EstablishmentWithImage
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun InstitutionCard(i: Int, institutionCardState: Array<EstablishmentWithImage>) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Card(
        modifier = Modifier.padding(start = 10.dp), shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier.height(140.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            if (institutionCardState[i].image != null) {
                Image(
                    painter = institutionCardState[i].image!!,
                    contentDescription = "Restaurant Card",
                    modifier = Modifier.width(140.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Box(Modifier.matchParentSize().background(gradient))
            Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = institutionCardState[i].name,
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
                        text = institutionCardState[i].rating.toString(),
                        style = MaterialTheme.typography.body1,
                        color = mainBlack,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally).wrapContentWidth()
                    )
                }
            }
        }
    }
}
