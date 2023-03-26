package fit.budle.components.atoms.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.ui.theme.*

@Composable
fun BudleDropDownMenu(
    modifier: Modifier = Modifier,
    startMessage: String,
    placeHolder: String,
    items: MutableList<String>,
) {

    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(startMessage) }

    val dropDownIcon = if (!isExpanded) R.drawable.chevron_down else R.drawable.chevron_up
    val textColor = if (selectedItem != startMessage) mainBlack else textGray
    val onSelect: (String) -> (Unit) = { selectedItem = it }
    val isSelected: (String) -> (Boolean) = { it == selectedItem }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium,
                color = mainBlack,
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(lightBlue)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 10.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedItem,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor,
                    )
                    IconButton(
                        onClick = { isExpanded = !isExpanded }
                    ) {
                        Icon(
                            painter = painterResource(id = dropDownIcon),
                            contentDescription = "Down",
                            tint = textGray
                        )
                    }
                }
            }
        }
        if (isExpanded) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                itemsIndexed(items) { _, item ->
                    BudleDropDownMenuItem(
                        modifier = Modifier.padding(top = 15.dp),
                        item = item,
                        isSelected = isSelected,
                        onSelect = onSelect
                    )
                }
            }
        }
    }
}