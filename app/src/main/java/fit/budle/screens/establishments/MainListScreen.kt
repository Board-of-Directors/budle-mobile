package fit.budle.screens.establishments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.models.Establishments
import fit.budle.models.establishments
import fit.budle.navigation.navigate
import fit.budle.ui.theme.*

@Composable
fun MainListScreen(navController: NavHostController) {

    val establishmentState = remember { establishments }
    val columnState = rememberLazyListState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // Main column
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            ShowSearchBar(navController)
            // List of institutions
            LazyColumn(
                state = columnState,
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(establishmentState) { i, _ ->
                    ShowInstitution(
                        navController = navController,
                        establishmentState[i]
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSearchBar(navController: NavHostController) {
    val text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            modifier = Modifier.width(244.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {},
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = backgroundLightBlue,
            ),
            placeholder = {
                Text(
                    text = "Поиск",
                    style = MaterialTheme.typography.bodyMedium,
                    color = mainBlack
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search",
                    tint = mainBlack
                )
            }
        )
        IconButton(
            onClick = { },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.map),
                modifier = Modifier.padding(start = 20.dp),
                contentDescription = "Map",
                tint = mainBlack
            )
        }
        IconButton(
            onClick = {
                navController.navigate(route = "user_profile")
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.burger),
                modifier = Modifier.padding(start = 20.dp),
                contentDescription = "Burger",
                tint = mainBlack
            )
        }
    }
}

@Composable
fun ShowInstitution(
    navController: NavHostController,
    establishments: Establishments
) {

    val institutionCardState = remember { establishments.establishmentList }
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
            itemsIndexed(institutionCardState) { i, _ ->
                val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
                Card(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable(
                            onClick = {
                                navController.navigate(
                                    "establishment_card",
                                    bundleOf("EST_KEY" to institutionCardState[i])
                                )
                            }
                        ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Box(
                        modifier = Modifier.height(140.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Image(
                            painter = painterResource(id = institutionCardState[i].imgID),
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
                                Box(modifier = Modifier.align(CenterHorizontally)) {
                                    Text(
                                        text = institutionCardState[i].rate.toString(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = mainBlack,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}