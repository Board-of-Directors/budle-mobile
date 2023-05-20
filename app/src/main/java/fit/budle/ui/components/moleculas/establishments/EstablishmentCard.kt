package fit.budle.ui.components.moleculas.establishments

import android.util.Log
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
import fit.budle.di.establishment.Establishment
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.viewmodel.customer.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstablishmentCard(
    establishment: Establishment,
    navController: NavController,
    viewModel: MainViewModel
) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Card(
        modifier = Modifier.padding(start = 10.dp),
        shape = RoundedCornerShape(15.dp),
        onClick = {
            viewModel.establishmentCardId = establishment.id
            Log.d("ID", viewModel.establishmentCardId.toString())
            navController.navigate("card")
        }
    ) {
        Box(
            modifier = Modifier.size(140.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Box(
                modifier = Modifier.size(140.dp)
            ){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = establishment.image
                        ?: painterResource(
                            id = R.drawable.carddefault
                        ),
                    contentDescription = "Restaurant Card", //TODO Сделать нормальный дескриптор
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                Modifier
                    .matchParentSize()
                    .background(gradient)
            )
            Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = establishment.name,
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
                        text = establishment.rating.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
