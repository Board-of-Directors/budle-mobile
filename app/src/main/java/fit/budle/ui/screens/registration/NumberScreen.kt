package fit.budle.ui.screens.registration

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.customer.RegistrationEvent
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleNumberInput
import fit.budle.ui.models.NumberDefaults
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.RegistrationViewModel

@Composable
fun NumberScreen(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {
    viewModel.requestException.observeAsState().value.let {
        Log.d("MAINSCREEN", "ERROR UPDATED: $it")
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
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
                        viewModel.requestException.value = ""
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
                    placeHolder = stringResource(R.string.placeholder_number),
                    inputLength = NumberDefaults.INPUT_LENGTH,
                    mask = NumberDefaults.MASK,
                    onValueChange = { viewModel.phoneNumber = it },
                    exceptionMessage = if (viewModel.requestException.value != "SUCCESS") viewModel.requestException.value
                        ?: "" else ""
                )
            }
            Spacer(Modifier.weight(1f))
            BudleButton(
                onClick = {
                    viewModel.onEvent(RegistrationEvent.GetCode)
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (viewModel.requestException.value == "SUCCESS") {
                            navHostController.navigate(route = "codeScreen")
                        }
                    }, 3000)
                },
                buttonText = stringResource(R.string.btn_accept)
            )
        }
    }
}
