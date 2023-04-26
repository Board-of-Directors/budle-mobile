package fit.budle.ui.screens.business.creator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.ui.components.atoms.headers.BudleUserHeader
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
// import fit.budle.ui.components.moleculas.card_lists.BudleCreatorEstablishmentCardList
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButton
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.textGray

/*
@Composable
fun CreatorMainScreen(
    navHostController: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        BudleScreenWithButton(
            iconId = R.drawable.plus,
            buttonText = "Добавить заведение",
            onClick = {},
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
                onClick = {}
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
*/