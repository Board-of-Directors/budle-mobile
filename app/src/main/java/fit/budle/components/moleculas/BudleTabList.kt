package fit.budle.components.moleculas

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.BudleTab
import fit.budle.models.Tab
import fit.budle.models.tabs
import fit.budle.ui.theme.lightBlue

@Composable
fun BudleTabList(
        tabs: MutableList<Tab>,
        navController: NavHostController
) {
    LazyColumn(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
    ) {
        itemsIndexed(tabs) { _, tab ->
            Divider(thickness = 1.dp, color = lightBlue)
            BudleTab(navController = navController, tab = tab)
        }
    }
}