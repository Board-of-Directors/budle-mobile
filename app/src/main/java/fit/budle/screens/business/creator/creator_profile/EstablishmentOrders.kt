package fit.budle.screens.business.creator.creator_profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.cards.BudleBusinessOrderCard
import fit.budle.components.moleculas.BudleBusinessOrderCardList
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.components.moleculas.budleTagList
import fit.budle.models.BusinessEstablishmentModel
import fit.budle.models.bookingList
import fit.budle.models.statusList
import fit.budle.models.tagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentOrdersScreen(
        navHostController: NavHostController,
        establishment: BusinessEstablishmentModel
) {

    var currentType by remember {
        mutableStateOf("Все")
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
        ) {
            BudleNavigationHeader(
                    textMessage = "Заказы",
                    onClick = { navHostController.popBackStack() }
            )
            Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
            ){
                currentType = budleTagList(
                        initialState = statusList[0],
                        tagList = statusList
                ).tagName
            }
            BudleBusinessOrderCardList(
                    bookingList = establishment.orders,
                    filter = currentType
            )
        }
    }
}