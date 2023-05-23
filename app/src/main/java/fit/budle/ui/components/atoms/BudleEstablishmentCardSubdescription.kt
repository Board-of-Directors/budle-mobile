package fit.budle.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.textGray

@Composable
fun BudleEstablishmentSubdescription(
    type: String,
    subtype: String? = null,
) {
    Row(
        modifier = Modifier.padding(top = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.labelSmall,
            color = textGray
        )
        if (subtype != null) {
            Box(modifier = Modifier
                .size(3.dp)
                .padding(horizontal = 3.dp)
                .background(textGray))
            Text(
                text = subtype,
                style = MaterialTheme.typography.labelSmall,
                color = textGray
            )
        }
    }
}