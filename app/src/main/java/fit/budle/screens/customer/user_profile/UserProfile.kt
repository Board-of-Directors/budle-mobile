package fit.budle.screens.customer.user_profile

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
import fit.budle.components.atoms.BudleApplicationPattern
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.headers.BudleUserHeader
import fit.budle.components.moleculas.BudleTabList
import fit.budle.models.Tab
import fit.budle.models.tabs
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.*

@Composable
fun UserProfileScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Column(
                Modifier.fillMaxSize()
        ) {
            BudleUserHeader(
                    modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                    title = "Артём Третьяков",
                    iconId = R.drawable.x,
                    iconDescription = "Close",
                    iconColor = mainBlack,
                    onClick = {
                        navController.navigate(NavRoute.MainPage.route)
                    }
            )
            Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
            ) {
                BudleTabList(tabs, navController)
            }
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
                        navController.navigate(NavRoute.BusinessMain.route)
                    },
                    topPadding = 15.dp,
                    iconId = R.drawable.zap,
                    buttonText = "Создать бизнес-аккаунт",
                    disabledButtonColor = lightBlue,
                    activeButtonColor = fillPurple,
                    disabledTextColor = textGray,
                    activeTextColor = mainWhite
            )
            BudleApplicationPattern()
        }
    }
}