package fit.budle.ui.components.moleculas

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fit.budle.R
import fit.budle.event.customer.MainEvent
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.viewmodel.customer.MainViewModel

@Composable
fun BudleSearchBar(
    navHostController: NavController,
    viewModel: MainViewModel,
) {

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = viewModel.selectedEstablishmentName,
            modifier = Modifier.width(244.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { viewModel.selectedEstablishmentName = it },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundLightBlue,
                unfocusedContainerColor = backgroundLightBlue,
                disabledContainerColor = backgroundLightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
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
            onClick = {
                viewModel.isFiltersVisible = true
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "Map",
                tint = mainBlack
            )
        }
        Spacer(Modifier.width(5.dp))
        IconButton(
            onClick = {
                navHostController.navigate(route = "userProfile")
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.burger_svg),
                contentDescription = "Burger",
                tint = mainBlack
            )
        }
        viewModel.onEvent(MainEvent.GetFilteredEstablishments)
    }
}