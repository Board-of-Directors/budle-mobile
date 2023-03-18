package fit.budle.components.atoms

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.models.EstablishmentDescription
import fit.budle.models.establishmentDescriptionList
import fit.budle.ui.theme.*

@Composable
fun BudleEstablishmentCardDescription(establishmentDescription: EstablishmentDescription) {
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
                Image(
                    painter = painterResource(id = establishmentDescription.imgID),
                    contentDescription = "Restaurant Card",
                    modifier = Modifier.width(108.dp),
                    contentScale = ContentScale.Crop
                )
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
                                text = establishmentDescription.rate.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = mainBlack,
                            )
                        }
                    }
                    if (establishmentDescription.isFavorite){
                        Icon(
                            modifier = Modifier.padding(start = 23.dp),
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "Favorite establishment",
                            tint = backgroundError
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(
                text = establishmentDescription.name,
                style = MaterialTheme.typography.bodyMedium,
                color = mainBlack
            )
            Row(
                modifier = Modifier.padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = establishmentDescription.type,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
                Box(modifier = Modifier.size(3.dp))
                Text(
                    text = establishmentDescription.subtype,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = establishmentDescription.subway,
                style = MaterialTheme.typography.labelSmall,
                color = mainBlack
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = establishmentDescription.place,
                style = MaterialTheme.typography.labelSmall,
                color = mainBlack
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun PreivewBudleEstablishmentCardDescription() {
    BudleEstablishmentCardDescription(
        establishmentDescription = establishmentDescriptionList[0]
    )
}