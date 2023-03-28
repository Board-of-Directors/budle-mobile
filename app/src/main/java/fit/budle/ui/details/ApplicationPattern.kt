package fit.budle.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import fit.budle.R

@Composable
fun ApplicationPattern() {
    Box(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            imageVector = ImageVector.vectorResource(id = R.drawable.budle_pattern),
            contentDescription = "Budle pattern"
        )
    }
}
