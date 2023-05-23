package fit.budle.ui.screens.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.Tab
import fit.budle.dto.customer_user.RequestUser
import fit.budle.dto.tabs
import fit.budle.event.customer.UserProfileEvent
import fit.budle.ui.components.BudleApplicationPattern
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.headers.BudleUserHeader
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.UserProfileViewModel

@Composable
fun UserProfileScreen(
    navController: NavHostController,
    viewModel: UserProfileViewModel,
) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize()
        ) {
            BudleUserHeader(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                title = viewModel.username,
                iconId = R.drawable.x,
                iconDescription = "Close",
                iconColor = mainBlack,
                onClick = { navController.popBackStack() }
            )
            ShowColumn(navController)
            BudleButton(
                onClick = {
                    viewModel.onEvent(UserProfileEvent.Logout)
                    if (viewModel.isLogin) {
                        navController.navigate("main")
                    } else {
                        navController.navigate("registration/")
                    }
                },
                iconId = R.drawable.log_out,
                buttonText = viewModel.buttonText,
                disabledButtonColor = lightBlue,
                activeButtonColor = fillPurple,
                disabledTextColor = fillPurple,
                activeTextColor = mainWhite
            )
            BudleButton(
                onClick = {
                    viewModel.onEvent(
                        UserProfileEvent.PostBusinessLoginEvent(
                            RequestUser(
                                "1234557",
                                "+79139391192",
                                "artem3"
                            )
                        )
                    )
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
            viewModel.onEvent(UserProfileEvent.GetSession)
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
    tab: Tab,
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
