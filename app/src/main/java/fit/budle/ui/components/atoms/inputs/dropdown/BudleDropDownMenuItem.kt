package fit.budle.ui.components.atoms.inputs.dropdown

import android.graphics.drawable.PictureDrawable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.caverock.androidsvg.SVG
import fit.budle.R
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BudleDropDownMenuItem(
    modifier: Modifier = Modifier,
    item: String,
    iconPath: String? = null,
    isSelected: (String) -> (Boolean),
    onSelect: (String) -> (Unit),
) {

    val startPadding = if (iconPath != null) 20.dp else 0.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onSelect(item)
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconPath != null) {

                val svg = SVG.getFromString(iconPath)
                val drawable = PictureDrawable(svg.renderToPicture(100, 100))

                GlideImage(
                    model = drawable,
                    contentDescription = "Icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(100.dp)
                )

            }
            Text(
                modifier = Modifier.padding(start = startPadding),
                text = item,
                style = MaterialTheme.typography.bodyMedium,
                color = mainBlack,
            )
        }
        if (isSelected(item)) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "Check",
                tint = fillPurple
            )
        }
    }
}