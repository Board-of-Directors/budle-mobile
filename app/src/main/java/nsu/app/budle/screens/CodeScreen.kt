package nsu.app.budle.screens

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budle.R
import kotlinx.coroutines.delay
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.ui.theme.backgroundError
import nsu.app.budle.ui.theme.backgroundLightBlue
import nsu.app.budle.ui.theme.fillPurple
import nsu.app.budle.ui.theme.textGray

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CodeScreen(navController: NavHostController) {

    val focusManager = LocalFocusManager.current
    val states = remember {
        mutableStateListOf(
            "", "", "", ""
        )
    }
    val listState = rememberLazyListState()
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
                    onClick = { navController.navigate(route = NavRoute.Number.route) },
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
                    state = listState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(states) { i, _ ->
                        val inputColor = if (!errorState.value || states[i].isNotEmpty())
                            backgroundLightBlue else backgroundError
                        // start from first input
                        if (i == 0 || states[i - 1] != "") {
                            TextField(
                                value = states[i],
                                modifier = Modifier
                                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                                    .width(60.dp)
                                    // change focus when backspace
                                    .onKeyEvent { event: KeyEvent ->
                                        if (event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Backspace &&
                                            states[i].isEmpty()
                                        ) {
                                            focusManager.moveFocus(FocusDirection.Left)
                                            true
                                        } else false
                                    },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                onValueChange = {
                                    if (it.length <= 1) {
                                        if (i == 0 || states[i - 1] != "") {
                                            if (errorState.value) errorState.value = false
                                            states[i] = it
                                            if (states[i] != ""){
                                                focusManager.moveFocus(FocusDirection.Right)
                                            }
                                            else focusManager.moveFocus(FocusDirection.Left)
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
                        } else if (states[i - 1] == "") {
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
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                },
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
                    errorState.value = states.contains("")
                    if (!errorState.value){
                        navController.navigate(route = NavRoute.Data.route)
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
