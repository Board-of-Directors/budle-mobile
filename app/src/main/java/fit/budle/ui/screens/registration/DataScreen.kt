package fit.budle.ui.screens.registration

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.enums.RegisterType
import fit.budle.event.customer.RegistrationEvent
import fit.budle.ui.components.atoms.inputs.text_fields.BudlePasswordTextField
import fit.budle.ui.components.atoms.inputs.text_fields.BudleSingleLineTextField
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.RegistrationViewModel

@Composable
fun DataScreen(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {

    val nextButtonText = if (viewModel.type == RegisterType.REGISTER) "Далее" else "Подтвердить"
    val passwordColor = if (viewModel.requestException.isEmpty()) textGray else backgroundError
    val passwordMessage = viewModel.requestException.ifEmpty {
        "Придумайте пароль от 8 знаков из\n" + "цифр и латинских букв"
    }

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
                        if (viewModel.type == RegisterType.REGISTER) {
                            navHostController.popBackStack()
                            viewModel.requestException = ""
                        } else navHostController.navigate("startScreen")
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
            ) {
                Text(
                    text = "Имя пользователя",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                BudleSingleLineTextField(
                    startMessage = "",
                    placeholder = "Введите Ваше имя",
                    onValueChange = { viewModel.username = it }
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "Пароль",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                BudlePasswordTextField(
                    startMessage = "",
                    placeholder = "Введите пароль",
                    onValueChange = { viewModel.password = it }
                )
                Text(
                    text = passwordMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = passwordColor,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    if (viewModel.username.isNotEmpty() && viewModel.password.length > 8) {
                        viewModel.onEvent(RegistrationEvent.PostUser(viewModel.type))
                        Handler(Looper.getMainLooper()).postDelayed({
                            if (viewModel.requestException.isEmpty()) {
                                if (viewModel.type == RegisterType.REGISTER) {
                                    navHostController.navigate("endScreen")
                                } else {
                                    navHostController.navigate("main")
                                }
                            }
                        },1000)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = fillPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 90.dp)
            ) {
                Text(
                    text = nextButtonText,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}