package fit.budle.components.moleculas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray

@Composable
fun BudleNavigationHeader(
    percent: String? = null,
    textMessage: String,
    route: String,
    navController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.navigate(route = route)
                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_left_gray),
                    contentDescription = "Arrow Left",
                    tint = textGray
                )
            }
            Text(
                text = textMessage,
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }
        if (percent != null) {
            Text(
                text = percent,
                style = MaterialTheme.typography.titleSmall,
                color = fillPurple
            )
        }

    }
}