package fit.budle.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import fit.budle.R
import fit.budle.ui.details.BudleButton
import fit.budle.ui.details.IconButton
import fit.budle.ui.details.PhotoTag
import fit.budle.ui.details.BlockWithHeader
import fit.budle.ui.details.InfoTagList
import fit.budle.model.establishment.Establishment
import fit.budle.model.WorkingHour
import fit.budle.ui.theme.*

@Composable
fun CardScreen(
    navController: NavController, establishment: Establishment?
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(10f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(modifier = Modifier.size(26.dp),
                    iconDescription = "Close",
                    iconId = R.drawable.x,
                    onClick = {
                        navController.navigate("home")
                    })
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .weight(1f, false)
            ) {
                BudleButton(
                    onClick = {
                        if (establishment != null) {
                            navController.navigate(
                                "order/${establishment.id}/${establishment.name}"
                            )
                        }
                    },
                    buttonText = "Забронировать место",
                    disabledButtonColor = fillPurple,
                    activeButtonColor = fillPurple,
                    disabledTextColor = mainWhite,
                    activeTextColor = mainWhite,
                    horizontalPadding = 20.dp
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            if (establishment != null) {
                EstablishmentBanner(
                    establishmentCard = establishment
                )
                Log.wtf("NULL", "NOT_NULL")
            } else {
                Log.wtf("NULL", "NULL")
            }
            InfoBar(establishmentCard = establishment)
            if (establishment != null) {
                InfoTagList(tags = establishment.tags)
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                if (establishment != null) {
                    establishment.description?.let {
                        EstablishmentCardDescription(
                            description = it
                        )
                    }
                }
                if (establishment != null) {
                    WorkingTime(cardDescription = establishment.workingHours)
                }
                if (establishment != null) {
                    EstablishmentAddress(addressInfo = establishment.address)
                }
            }
        }
    }
}

@Composable
fun EstablishmentBanner(
    establishmentCard: Establishment
) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Box(
        modifier = Modifier
            .padding(0.dp)
            .height(200.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        establishmentCard.image?.let {
            Image(
                painter =
                it,
                contentDescription = "Restaurant Card",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            Modifier
                .matchParentSize()
                .background(gradient)
        )
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(bottom = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = establishmentCard.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = mainWhite,
                    )
                    Card(
                        modifier = Modifier.padding(horizontal = 7.dp), shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier
                                .size(3.dp)
                                .background(mainWhite)
                        )
                    }
                    establishmentCard.cuisineCountry?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            color = mainWhite
                        )
                    }
                }
                Text(
                    text = establishmentCard.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = mainWhite,
                )
            }
        }
    }
}

@Composable
fun InfoBar(
    establishmentCard: Establishment?
) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (establishmentCard != null) {
                PhotoTag(
                    tag = establishmentCard.rating.toString(),
                    color = fillPurple,
                    textColor = mainWhite
                )
            }
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "Рейтинг",
                style = MaterialTheme.typography.bodyMedium,
                color = textGray
            )
        }
        Row(Modifier.width(IntrinsicSize.Max)) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "Share icon",
                    tint = textGray
                )
            }
        }
    }
}

@Composable
fun EstablishmentCardDescription(
    description: String
) {
    BlockWithHeader(headerText = "Описание") {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = mainBlack
        )
    }
}

@Composable
fun WorkingTime(
    cardDescription: Array<WorkingHour>
) {
    BlockWithHeader(headerText = "Время работы") {
        Column {
            for (i in cardDescription.indices) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
                    Text(
                        modifier = Modifier.width(50.dp),
                        text = cardDescription[i].dayOfWeek,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = cardDescription[i].startTime + "-" + cardDescription[i].endTime,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                }
            }
        }
    }
}

@Composable
fun EstablishmentAddress(
    addressInfo: String
) {
    BlockWithHeader(
        modifier = Modifier.padding(bottom = 100.dp), headerText = "Адрес"
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.height(IntrinsicSize.Max)
            ) {
                Text(
                    text = addressInfo,
                    style = MaterialTheme.typography.bodyMedium,
                    color = mainBlack
                )
            }
        }
    }
}
