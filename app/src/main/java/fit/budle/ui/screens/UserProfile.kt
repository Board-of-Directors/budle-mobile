package fit.budle.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.ui.details.ApplicationPattern
import fit.budle.ui.details.BudleButton
import fit.budle.ui.details.UserHeader
import fit.budle.model.Tab
import fit.budle.model.tabs
import fit.budle.navigation.NavRoute
import fit.budle.ui.theme.*

@Composable
fun UserProfileScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize()
        ) {
            UserHeader(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                title = "Артём Третьяков",
                iconId = R.drawable.x,
                iconDescription = "Close",
                iconColor = mainBlack,
                navController = navController
            )
            ShowColumn(navController)
            BudleButton(
                onClick = {
                    navController.navigate(NavRoute.MainPage.route)
                },
                iconId = R.drawable.log_out,
                buttonText = "Выйти",
                disabledButtonColor = lightBlue,
                activeButtonColor = fillPurple,
                disabledTextColor = fillPurple,
                activeTextColor = mainWhite
            )
            BudleButton(
                onClick = {
                },
                topPadding = 15.dp,
                iconId = R.drawable.zap,
                buttonText = "Создать бизнес-аккаунт",
                disabledButtonColor = lightBlue,
                activeButtonColor = fillPurple,
                disabledTextColor = textGray,
                activeTextColor = mainWhite
            )
            ApplicationPattern()
        }
    }
}

@Composable
fun ShowColumn(navController: NavHostController) {
    val institutionStates = remember { tabs }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        itemsIndexed(institutionStates) { i, _ ->
            Divider(thickness = 1.dp, color = lightBlue)
            ShowTab(navController = navController, tab = institutionStates[i])
        }
    }
}

@Composable
fun ShowTab(
    navController: NavHostController,
    tab: Tab
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 25.dp)
            .clickable(
                onClick = {
                    navController.navigate(route = tab.route)
                }
            )
    ) {
        Icon(
            painter = painterResource(id = tab.iconID),
            contentDescription = tab.text,
            tint = tab.color ?: textGray
        )
        Text(
            text = tab.text,
            modifier = Modifier.padding(start = 15.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
