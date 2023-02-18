package fit.budle.ui.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import fit.budle.ui.details.BudleTest

@Composable
fun DetailScreen(navController: NavController, numberProvider: (String) -> String) {
    val number = "+79231095499"
    Button(onClick = { navController.navigate("detail") }) {
        Text(text = "Go to detail")
    }
    BudleTest(numberProvider(number))
}
