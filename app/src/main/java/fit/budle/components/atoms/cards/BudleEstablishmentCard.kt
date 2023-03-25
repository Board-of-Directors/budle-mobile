package fit.budle.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.models.EstablishmentCard
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleEstablishmentCard(establishmentCard: EstablishmentCard) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Card(
        modifier = Modifier.padding(start = 10.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier.height(140.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = establishmentCard.imgID),
                contentDescription = "Restaurant Card",
                modifier = Modifier.width(140.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                Modifier
                    .matchParentSize()
                    .background(gradient)
            )
            Column(Modifier.padding(15.dp)) {
                Text(
                    text = establishmentCard.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = mainWhite,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier
                        .width(38.dp)
                        .height(20.dp),
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = establishmentCard.rate.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = mainBlack,
                        )
                    }
                }
            }
        }
    }
}