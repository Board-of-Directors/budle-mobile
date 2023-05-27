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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.customer.RegistrationEvent
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.RegistrationViewModel

@Composable
fun CodeScreen(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {
    val focusManager = LocalFocusManager.current
    val firstInput = 0
    val lastInput = 3
    val errorState = remember { mutableStateOf(false) }

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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 60.dp),
            ) {
                Text(
                    text = "Подтверждение номера",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "На Ваш телефон придёт СМС.\n" + "Введите его в поле ниже.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 60.dp),
                    textAlign = TextAlign.Center
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(viewModel.states) { i, _ ->
                        val inputColor = if (!errorState.value || viewModel.states[i].isNotEmpty())
                            backgroundLightBlue else backgroundError
                        TextField(
                            value = viewModel.states[i],
                            modifier = Modifier
                                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                                .width(60.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                if (it.length <= 1) {
                                    viewModel.states[i] = it
                                }
                                if (it.isNotEmpty()) {
                                    if (i == lastInput) {
                                        focusManager.clearFocus()
                                    } else {
                                        focusManager.moveFocus(FocusDirection.Next)
                                    }
                                } else {
                                    if (i == firstInput) {
                                        focusManager.clearFocus()
                                    } else {
                                        focusManager.moveFocus(FocusDirection.Previous)
                                    }
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = fillPurple,
                                unfocusedIndicatorColor = inputColor,
                                disabledIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            ),
                            textStyle = MaterialTheme.typography.displayLarge
                        )
                    }
                }
                if (viewModel.requestException.isNotEmpty()) {
                    Text(
                        text = viewModel.requestException,
                        style = MaterialTheme.typography.bodyMedium,
                        color = backgroundError,
                        modifier = Modifier.padding(top = 10.dp, start = 15.dp)
                            .fillMaxWidth()
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = backgroundLightBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Новый код - 2:56",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray
                )
            }
            Button(
                onClick = {
                    errorState.value = viewModel.states.contains("")
                    if (!errorState.value) {
                        viewModel.onEvent(RegistrationEvent.PostCode)
                        Handler(Looper.getMainLooper()).postDelayed({
                            if (viewModel.requestException.isEmpty()) {
                                navHostController.navigate("dataScreen")
                            } else {
                                for (i in 0..3) {
                                    viewModel.states[i] = ""
                                }
                            }
                        }, 1000)
                    }
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