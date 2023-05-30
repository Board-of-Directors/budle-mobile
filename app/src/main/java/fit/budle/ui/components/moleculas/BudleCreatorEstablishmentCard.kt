package fit.budle.ui.components.moleculas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.ui.components.atoms.BudleEstablishmentSubdescription
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray

@Composable
fun BudleCreatorEstablishmentCard(
    navHostController: NavHostController,
    establishmentCard: EstablishmentShortDto,
    onDelete: (Int) -> Unit,
) {

    var isOpened by remember { mutableStateOf(false) }
    val iconId = if (isOpened) R.drawable.minus else R.drawable.plus
    val iconDescription = if (isOpened) "Close" else "Open"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .height(IntrinsicSize.Max),
        border = BorderStroke(2.dp, lightBlue),
        colors = CardDefaults.cardColors(mainWhite),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = establishmentCard.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    BudleEstablishmentSubdescription(
                        type = establishmentCard.category
                    )
                }
                IconButton(
                    onClick = { isOpened = !isOpened }
                ) {
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = iconDescription,
                        tint = fillPurple
                    )
                }
            }
            if (isOpened) {
                Divider(
                    modifier = Modifier.padding(top = 20.dp),
                    thickness = 1.dp,
                    color = lightBlue
                )
                Column {
                    TextWithIcon(
                        onClick = {
                            navHostController.navigate("workers/${establishmentCard.id}")
                        },
                        text = "Сотрудники",
                        iconId = R.drawable.user,
                        iconDescription = "Сотрудники"
                    )
                    TextWithIcon(
                        onClick = {
                            navHostController.navigate("bookings/${establishmentCard.id}")
                        },
                        text = "Заказы",
                        iconId = R.drawable.file,
                        iconDescription = "Заказы"
                    )
                    TextWithIcon(
                        onClick = {
                            onDelete(establishmentCard.id)
                        },
                        text = "Удалить",
                        color = backgroundError,
                        iconId = R.drawable.trash,
                        iconDescription = "Редактировать"
                    )
                }
            }
        }
    }
}

@Composable
fun TextWithIcon(
    onClick: () -> Unit,
    text: String,
    iconId: Int,
    iconDescription: String,
    color: Color = fillPurple,
) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .height(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = iconDescription,
                tint = color
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = color
            )
        }
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = if (color != fillPurple) backgroundError else textGray
            )
        }
    }
}