package fit.budle.ui.components.moleculas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.dto.establishment.etsablishment_type.EstablishmentShortDto
import fit.budle.ui.components.atoms.BudleEstablishmentSubdescription
import fit.budle.ui.theme.*

@Composable
fun BudleCreatorEstablishmentCard(
    navHostController: NavHostController,
    establishmentCard: EstablishmentShortDto,
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
                        onClick = {},
                        text = "Редактировать",
                        iconId = R.drawable.edit,
                        iconDescription = "Редактировать"
                    )
                    TextWithIcon(
                        onClick = {
                            navHostController.navigate("ownerMain/workers/${establishmentCard.id}")
                        },
                        text = "Сотрудники",
                        iconId = R.drawable.user,
                        iconDescription = "Сотрудники"
                    )
                    TextWithIcon(
                        onClick = {
                            navHostController.navigate("ownerMain/bookings/${establishmentCard.id}")
                        },
                        text = "Заказы",
                        iconId = R.drawable.file,
                        iconDescription = "Заказы"
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
                tint = fillPurple
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = mainBlack
            )
        }
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = textGray
            )
        }
    }
}