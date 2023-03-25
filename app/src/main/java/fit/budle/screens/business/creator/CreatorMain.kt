package fit.budle.screens.business.creator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.headers.BudleUserHeader
import fit.budle.components.moleculas.BudleBlockWithHeader
import fit.budle.components.moleculas.BudleCreatorEstablishmentCardList
import fit.budle.components.moleculas.BudleScreenWithButton
import fit.budle.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.models.businessEstablishmentList
import fit.budle.navigation.routes.NavRoute
import fit.budle.navigation.routes.NestedGraphRoute
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray

@Composable
fun CreatorMainScreen(
    navHostController: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        BudleScreenWithButton(
            navHostController = navHostController,
            iconId = R.drawable.plus,
            buttonText = "Добавить заведение",
            route = NavRoute.EstablishmentCreationFirst.route,
        ) {
            BudleUserHeader(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                title = "Артём Третьяков",
                subTitle = "Создатель",
                iconId = R.drawable.log_out,
                iconDescription = "Log out",
                iconColor = textGray,
                onClick = {
                    navHostController.navigate(NavRoute.UserProfile.route)
                }
            )
            Divider(
                modifier = Modifier.padding(top = 20.dp),
                thickness = 1.dp,
                color = lightBlue
            )
            BudleBlockWithHeader(
                headerPadding = 10.dp,
                headerText = "Мои заведения"
            ) {
                BudleCreatorEstablishmentCardList(
                    navHostController = navHostController,
                    cards = businessEstablishmentList
                )
            }
        }
    }
}