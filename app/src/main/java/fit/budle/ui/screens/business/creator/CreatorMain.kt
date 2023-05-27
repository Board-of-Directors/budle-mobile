package fit.budle.ui.screens.business.creator

// import fit.budle.ui.components.moleculas.card_lists.BudleCreatorEstablishmentCardList
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.business.OwnerMainEvent
import fit.budle.ui.components.atoms.headers.BudleUserHeader
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.card_lists.BudleCreatorEstablishmentCardList
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButton
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.business.OwnerMainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CreatorMainScreen(
    navHostController: NavHostController,
    viewModel: OwnerMainViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        BudleScreenWithButton(
            iconId = R.drawable.plus,
            buttonText = "Добавить заведение",
            onClick = {
                  navHostController.navigate("ownerMain/establishmentCreation/")
            },
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
                    navHostController.navigate("userProfile")
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
                    cards = viewModel.establishments
                )
            }
            viewModel.onEvent(OwnerMainEvent.GetEstListEvent)
        }
    }
}