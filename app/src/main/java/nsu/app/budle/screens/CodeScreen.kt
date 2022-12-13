package nsu.app.budle.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.budle.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nsu.app.budle.Answer
import nsu.app.budle.checkCode
import nsu.app.budle.getCode
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.ui.theme.backgroundError
import nsu.app.budle.ui.theme.backgroundLightBlue
import nsu.app.budle.ui.theme.fillPurple
import nsu.app.budle.ui.theme.textGray
import org.json.JSONObject
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CodeScreen(navController: NavHostController, phoneNumber: String?) {
    val focusManager = LocalFocusManager.current
    val states = remember {
        mutableStateListOf(
            "", "", "", ""
        )
    }
    val listState = rememberLazyListState()
    val errorState = remember { mutableStateOf("") }

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
            Spacer(Modifier.weight(0.5f))
            Text(
                text = "Подтверждение номера",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "На Ваш телефон придёт СМС.\n" + "Введите его в поле ниже.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.weight(0.5f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyRow(
                    state = listState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(states) { i, _ ->
                        TextField(
                            value = states[i],
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .width(60.dp)
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
                            onValueChange = { text ->
                                if (text.length <= 1) {
                                    if (i == 0 || states[i - 1] != "") {
                                        if (text.isDigitsOnly()) {
                                            states[i] = text
                                            if (states[i] != "") {
                                                focusManager.moveFocus(FocusDirection.Right)
                                            } else focusManager.moveFocus(FocusDirection.Left)
                                        }
                                    }
                                } else focusManager.moveFocus(FocusDirection.Right)
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = fillPurple,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = Color.Transparent,
                                unfocusedIndicatorColor = backgroundLightBlue
                            ),
                            textStyle = MaterialTheme.typography.displayLarge
                        )
                    }
                }
                if (errorState.value.isNotEmpty()) {
                    Text(
                        textAlign = TextAlign.Left,
                        text = errorState.value,
                        style = MaterialTheme.typography.bodyMedium,
                        color = backgroundError,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Spacer(Modifier.weight(0.5f))
            var ticks by remember { mutableStateOf(30) }
            var run by remember { mutableStateOf(true) }
            if (ticks == 0) {
                run = false
            }
            LaunchedEffect(key1 = run) {
                while (run) {
                    delay(1.seconds)
                    ticks--
                }
            }
            Button(
                onClick = {
                    if (!run) {
                        ticks = 30
                        run = true
                        CoroutineScope(Dispatchers.Main).launch {
                            getCode(phoneNumber!!)
                        }
                    }
                },
                colors = if (ticks == 0) (ButtonDefaults.buttonColors(containerColor = fillPurple)) else ButtonDefaults.buttonColors(
                    containerColor = backgroundLightBlue
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = if (ticks != 0) "Новый код - $ticks" else "Отправить код",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (ticks != 0) textGray else Color.White
                )
            }
            Button(
                onClick = {
                    errorState.value = ""
                    var data: Answer?
                    CoroutineScope(Dispatchers.Main).launch {
                        data = checkCode(phoneNumber!!, states.joinToString(""))
                        if (data != null) {
                            if (!data!!.success) {
                                errorState.value = data!!.exception?.message ?: "Неизвестная ошибка"
                            }
                        } else {
                            errorState.value = "Ошибка сервера"
                        }
                        if (errorState.value.isEmpty()) {
                            val jsonObject = JSONObject()
                            jsonObject.put("buttonName", "Подтвердить")
                            jsonObject.put("phoneNumber", phoneNumber)
                            navController.navigate("data_screen/$jsonObject")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = fillPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "Подтвердить",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(Modifier.weight(0.5f))
        }
    }
}
