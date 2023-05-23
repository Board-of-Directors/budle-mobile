package fit.budle.ui.components.atoms.inputs.dropdown

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.dto.tag.standard.TagResponse
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleMultiSelectableDropDownMenu(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> (Unit),
    selectedItems: SnapshotStateList<String>,
    startMessage: String,
    placeHolder: String,
    items: List<TagResponse>,
) {

    var isExpanded by remember { mutableStateOf(false) }

    val dropDownIcon = if (!isExpanded) R.drawable.chevron_down else R.drawable.chevron_up
    val textColor = if (!selectedItems.isEmpty()) mainBlack else textGray
    val inputMessage = if (selectedItems.isEmpty()) startMessage else "Выбрано ${selectedItems.size} тега"

    val isSelected: (String) -> (Boolean) = { selectedItems.contains(it) }

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
                        text = inputMessage,
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
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                for (item in items) {
                    BudleDropDownMenuItem(
                        modifier = Modifier.padding(top = 15.dp),
                        item = item.name,
                        iconPath = item.image,
                        isSelected = isSelected,
                        onSelect = onValueChange
                    )
                }
            }
        }
    }
}