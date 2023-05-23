package fit.budle.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray

@Composable
fun BudleInformationScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    message: String,
    description: String? = null,
    imageId: Int,
    imageDescription: String,
    buttonText: String,
    disabledButtonColor: Color = fillPurple,
    disabledTextColor: Color = mainWhite,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = imageDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 60.dp)
            )
        }
        Image(
            painter = painterResource(
                id = R.drawable.logo_big
            ),
            contentDescription = "Logo",
            modifier = Modifier
                .width(148.dp)
                .padding(bottom = 40.dp)
        )
        Column(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
            )
            if (description != null) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    textAlign = TextAlign.Center,
                )
            }
        }
        BudleButton(
            onClick = onClick,
            buttonText = buttonText,
            disabledButtonColor = disabledButtonColor,
            disabledTextColor = disabledTextColor,
            horizontalPadding = 0.dp
        )
        Column {
            content()
        }
    }
}