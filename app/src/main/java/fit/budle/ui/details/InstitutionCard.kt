package fit.budle.ui.details

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fit.budle.R
import fit.budle.model.EstablishmentWithImage
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstitutionCard(
    i: Int,
    institutionCardState: Array<EstablishmentWithImage>,
    navController: NavController,
    category: String
) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Card(
        modifier = Modifier.padding(start = 10.dp), shape = RoundedCornerShape(15.dp), onClick = {
            navController.navigate("card/$category/$i")
        }
    ) {
        Box(
            modifier = Modifier.height(140.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = if (institutionCardState[i].image != null) institutionCardState[i].image!! else painterResource(
                    id = R.drawable.carddefault
                ),
                contentDescription = "Restaurant Card",
                modifier = Modifier.width(140.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                Modifier
                    .matchParentSize()
                    .background(gradient)
            )
            Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = institutionCardState[i].name,
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
                    Text(
                        text = institutionCardState[i].rating.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .wrapContentWidth()
                    )
                }
            }
        }
    }
}
