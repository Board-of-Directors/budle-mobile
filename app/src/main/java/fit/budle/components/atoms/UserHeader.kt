package fit.budle.components.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fit.budle.R
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleUserHeader(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    iconId: Int,
    iconDescription: String,
    iconColor: Color,
    navController: NavHostController,
    route: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.height(IntrinsicSize.Max),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = mainBlack
            )
            if (subTitle != null) {
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
            }
        }
        IconButton(
            onClick = {
                navController.navigate(route = "home")
            }
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = iconDescription,
                tint = iconColor
            )
        }
    }
}

@Preview
@Composable
fun PreviewBudleUserHeader(){
    BudleUserHeader(
        title = "Артём Третьяков",
        iconId = R.drawable.x,
        iconDescription = "Close",
        iconColor = mainBlack,
        navController = rememberNavController(),
        route = NavRoute.MainPage.route
    )
}