package fit.budle.ui.components.atoms.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import fit.budle.R
import fit.budle.dto.establishment.Establishment
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleEstablishmentCardDescription(
    establishment: Establishment,
) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(15.dp)
        ) {
            Box(
                modifier = Modifier.height(108.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Box(
                    Modifier.size(108.dp)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = establishment.image ?: painterResource(
                            id = R.drawable.carddefault
                        ),
                        contentDescription = "Restaurant Card",
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    Modifier
                        .matchParentSize()
                        .background(gradient)
                )
                Row(
                    modifier = Modifier.padding(start = 15.dp, bottom = 15.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(Color.White),
                        modifier = Modifier
                            .width(38.dp)
                            .height(20.dp),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Text(
                                text = establishment.rating.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = mainBlack,
                            )
                        }
                    }
                }
            }
        }
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(
                text = establishment.name,
                style = MaterialTheme.typography.bodyMedium,
                color = mainBlack
            )
            Row(
                modifier = Modifier.padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                val subtype: String = when (establishment.category) {
                    "Рестораны" -> establishment.cuisineCountry!!
                    "Отели" -> establishment.starsCount.toString() + " звезды"
                    else -> ""
                }

                Text(
                    text = establishment.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
                if (subtype.isNotEmpty()) {
                    Card(
                        modifier = Modifier.padding(horizontal = 7.dp), shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier
                                .size(3.dp)
                                .background(textGray)
                        )
                    }
                }
                Text(
                    text = subtype,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = establishment.address,
                style = MaterialTheme.typography.labelSmall,
                color = mainBlack
            )
        }
    }
}