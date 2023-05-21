package fit.budle.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fit.budle.R
import fit.budle.dto.establishment.EstablishmentRequest
import fit.budle.event.customer.MainEvent
import fit.budle.ui.components.moleculas.establishments.EstablishmentRow
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.viewmodel.customer.MainViewModel

@Composable
fun MainScreen(
    navHostController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val establishmentRequest =
        EstablishmentRequest(null, null, null, null, null, null, null)

    viewModel.onEvent(MainEvent.getCategory)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            ShowSearchBar(navHostController)
            LazyColumn(
                horizontalAlignment = Alignment.Start
            ) {
                itemsIndexed(viewModel.establishments2) { _, i ->
                    EstablishmentRow(
                        i.value,
                        navHostController,
                        viewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSearchBar(navHostController: NavController) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            modifier = Modifier.width(244.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { text = it },
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
        Spacer(Modifier.width(5.dp))
        IconButton(
            onClick = { },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Map",
                tint = mainBlack
            )
        }
        Spacer(Modifier.width(5.dp))
        IconButton(
            onClick = {
                navHostController.navigate(route = "user_profile")
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.burger),
                contentDescription = "Burger",
                tint = mainBlack
            )
        }
    }
}
