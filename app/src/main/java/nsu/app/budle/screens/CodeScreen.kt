package nsu.app.budle.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeScreen(navController: NavHostController) {
    val focusManager = LocalFocusManager.current
    val states = remember {
        mutableStateListOf(
            "", "", "", ""
        )
    }
    val listState = rememberLazyListState()
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
                        tint = Color(0xFFB6C1CE),
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
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 60.dp),
                    textAlign = TextAlign.Center
                )
                LazyRow(
                    state = listState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(states) { i, _ ->
                        TextField(
                            value = "",
                            modifier = Modifier
                                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                                .width(60.dp),
                            onValueChange = {
                                states[i] = it
                                focusManager.moveFocus(FocusDirection.Right)
                            },
                            textStyle = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEF5F9)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Новый код - 2:56",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFB6C1CE)
                )
            }
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.End.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF654DF6)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 90.dp)
            ) {
                Text(
                    text = "Подтвердить",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
