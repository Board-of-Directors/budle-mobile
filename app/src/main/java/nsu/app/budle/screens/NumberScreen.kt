package nsu.app.budle.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Arrow Left",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable { navController.navigate(route = NavRoute.Start.route) })
                Image(
                    painter = painterResource(
                        id = R.drawable.logo_big
                    ),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(148.dp)
                        .padding(end = 20.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 60.dp)
            ) {
                Text(
                    text = "Номер телефона",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFB6C1CE),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                var text by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = text,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        text = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color(0xFFEEF5F9)
                    )
                )
            }
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Code.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF654DF6)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 240.dp)
            ) {
                Text(
                    text = "Подвтердить",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
