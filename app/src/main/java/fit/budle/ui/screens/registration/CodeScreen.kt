package fit.budle.ui.screens.registration

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CodeScreen(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {

    val focusManager = LocalFocusManager.current
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
                        // start from first input
                        if (i == 0 || viewModel.states[i - 1] != "") {
                            TextField(
                                value = viewModel.states[i],
                                modifier = Modifier
                                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                                    .width(60.dp)
                                    // change focus when backspace
                                    .onKeyEvent { event: KeyEvent ->
                                        if (event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Backspace &&
                                            viewModel.states[i].isEmpty()
                                        ) {
                                            focusManager.moveFocus(FocusDirection.Left)
                                            true
                                        } else false
                                    },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                onValueChange = {
                                    if (it.length <= 1) {
                                        if (i == 0 || viewModel.states[i - 1] != "") {
                                            if (errorState.value) errorState.value = false
                                            viewModel.states[i] = it
                                            if (viewModel.states[i] != "") {
                                                focusManager.moveFocus(FocusDirection.Right)
                                            } else focusManager.moveFocus(FocusDirection.Left)
                                        }
                                    } else focusManager.moveFocus(FocusDirection.Right)
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = fillPurple,
                                    unfocusedIndicatorColor = inputColor,
                                    disabledIndicatorColor = Color.Transparent,
                                    containerColor = Color.Transparent
                                ),
                                textStyle = MaterialTheme.typography.displayLarge
                            )
                        } else if (viewModel.states[i - 1] == "") {
                            TextField(
                                value = "",
                                modifier = Modifier
                                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                                    .width(60.dp),
                                onValueChange = {},
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = inputColor,
                                    unfocusedIndicatorColor = inputColor,
                                    disabledIndicatorColor = Color.Transparent,
                                    containerColor = Color.Transparent,
                                ),
                                textStyle = MaterialTheme.typography.displayLarge,
                                readOnly = true
                            )
                        }
                    }
                }
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
                            } else viewModel.states = mutableStateListOf("", "", "", "")
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