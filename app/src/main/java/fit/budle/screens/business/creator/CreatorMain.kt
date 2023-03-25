package fit.budle.screens.business.creator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.BudleUserHeader
import fit.budle.components.moleculas.BudleBlockWithHeader
import fit.budle.components.moleculas.BudleCreatorEstablishmentCardList
import fit.budle.models.businessEstablishmentList
import fit.budle.models.sampleEstablishmentCardModel
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

    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .fillMaxSize()
                .zIndex(10f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, false)
            ) {
                BudleButton(
                    onClick = {
                        navHostController.navigate(NestedGraphRoute.EstablishmentCreation.route)
                    },
                    iconId = R.drawable.plus,
                    buttonText = "Добавить заведение",
                    disabledButtonColor = fillPurple,
                    disabledTextColor = mainWhite,
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
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
                navController = navHostController,
                route = NavRoute.UserProfile.route
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