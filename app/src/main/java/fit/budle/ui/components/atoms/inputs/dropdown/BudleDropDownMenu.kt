package fit.budle.ui.components.atoms.inputs.dropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@Composable
fun BudleDropDownMenu(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> (Unit),
    selectedItem: String?,
    isError: Boolean,
    startMessage: String,
    placeHolder: String,
    items: List<String>,
) {

    val strokeColor = if (!isError) Color.Transparent else backgroundError

    var isExpanded by remember { mutableStateOf(false) }

    val dropDownIcon = if (!isExpanded) R.drawable.chevron_down else R.drawable.chevron_up
    val textColor = if (selectedItem != null) mainBlack else textGray
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
                colors = CardDefaults.cardColors(lightBlue),
                border = BorderStroke(2.dp, strokeColor)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 10.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedItem ?: startMessage,
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
    }
    if (isExpanded) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()

        ) {
            for (item in items) {
                BudleDropDownMenuItem(
                    modifier = Modifier
                        .padding(top = 15.dp),
                    item = item,
                    isSelected = isSelected,
                    onSelect = onValueChange
                )
            }
        }
    }
    if (isError) {
        Text(
            text = "Это поле не может быть пустым",
            style = MaterialTheme.typography.bodyMedium,
            color = backgroundError,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}