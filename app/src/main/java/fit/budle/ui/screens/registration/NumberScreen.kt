package fit.budle.ui.screens.registration

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.customer.RegistrationEvent
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleNumberInput
import fit.budle.ui.models.NumberDefaults
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.RegistrationViewModel

@Composable
fun NumberScreen(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 64.dp)
            ) {
                IconButton(
                    onClick = {
                        navHostController.popBackStack()
                        viewModel.requestException = ""
                    },
                    modifier = Modifier.padding(end = 40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_gray),
                        contentDescription = "Arrow Left",
                        tint = textGray
                    )
                }
                Image(
                    painter = painterResource(
                        id = R.drawable.logo_big
                    ), contentDescription = "Logo", modifier = Modifier.width(148.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 60.dp)
                    .fillMaxWidth()
            ) {
                BudleNumberInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Номер телефона",
                    inputLength = NumberDefaults.INPUT_LENGTH,
                    mask = NumberDefaults.MASK,
                    onValueChange = { viewModel.phoneNumber = it }
                )
                if (viewModel.requestException.isNotEmpty()) {
                    Text(
                        text = viewModel.requestException,
                        style = MaterialTheme.typography.bodyMedium,
                        color = backgroundError,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onEvent(RegistrationEvent.GetCode)
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (viewModel.requestException.isEmpty()) {
                            navHostController.navigate(route = "codeScreen")
                        }
                    }, 1000)
                },
                colors = ButtonDefaults.buttonColors(containerColor = fillPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 90.dp)
            ) {
                Text(
                    text = "Подтвердить",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}