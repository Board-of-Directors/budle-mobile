package fit.budle.ui.screens.registration

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.customer.RegistrationEvent
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.inputs.text_fields.BudlePasswordTextField
import fit.budle.ui.components.atoms.inputs.text_fields.BudleSingleLineTextField
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.RegistrationViewModel

@Composable
fun DataScreenLogin(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    viewModel.requestException.observeAsState().value.let {
        Log.d("MAINSCREEN", "UPDATED: $it")
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
                    }, modifier = Modifier.padding(end = 40.dp)
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
            ) {
                Text(
                    text = stringResource(id = R.string.caption_user_name),
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                BudleSingleLineTextField(startMessage = "",
                    placeholder = stringResource(id = R.string.placeholder_user_name),
                    onValueChange = { viewModel.username = it })
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.caption_password),
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                BudlePasswordTextField(startMessage = "",
                    placeholder = stringResource(id = R.string.placeholder_password),
                    onValueChange = { viewModel.password = it })
                Text(
                    text = viewModel.requestException.value.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = backgroundError,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            BudleButton(
                onClick = {
                    if (viewModel.username.isNotEmpty()) {
                        viewModel.onEvent(RegistrationEvent.PostUser(viewModel.type))
                        Log.d("MEME", viewModel.requestException.value ?: "NULL")
                        viewModel.requestException.observe(lifecycleOwner) { exception ->
                            if (exception == "SUCCESS") {
                                navHostController.navigate("main")
                            }
                        }
                    }
                }, buttonText = stringResource(R.string.btn_accept)
            )
        }
    }
}
