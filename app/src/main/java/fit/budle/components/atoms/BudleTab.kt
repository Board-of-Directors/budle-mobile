package fit.budle.components.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.models.Tab
import fit.budle.ui.theme.textGray

@Composable
fun BudleTab(
        navController: NavHostController,
        tab: Tab
) {
    Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 25.dp)
                    .clickable(
                            onClick = {
                                navController.navigate(route = tab.route)
                            }
                    )
    ) {
        Icon(
                painter = painterResource(id = tab.iconID),
                contentDescription = tab.text,
                tint = tab.color ?: textGray
        )
        Text(
                text = tab.text,
                modifier = Modifier.padding(start = 15.dp),
                style = MaterialTheme.typography.bodyMedium
        )
    }
}