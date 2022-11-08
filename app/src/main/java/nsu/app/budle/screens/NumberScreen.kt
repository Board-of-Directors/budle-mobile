package nsu.app.budle.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute

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
        }
    }
}
