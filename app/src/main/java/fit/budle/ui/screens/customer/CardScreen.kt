package fit.budle.ui.screens.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fit.budle.R
import fit.budle.dto.WorkingHour
import fit.budle.dto.establishment.Establishment
import fit.budle.event.customer.MainEvent
import fit.budle.ui.components.BudleIconButton
import fit.budle.ui.components.BudleInfoTagList
import fit.budle.ui.components.BudlePhotoTag
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.MainViewModel

@Composable
fun CardScreen(
    navHostController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    viewModel.onEvent(MainEvent.GetEstablishment)

    ShowPhotoGallery(
        establishment = viewModel.establishmentCard,
        isClicked = viewModel.clickedGallery
    )
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
                BudleIconButton(modifier = Modifier.size(26.dp),
                    iconDescription = "Close",
                    iconId = R.drawable.x,
                    onClick = {
                        navHostController.navigate("main")
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
                        navHostController.navigate("orderCreation")
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
            EstablishmentBanner(
                establishmentCard = viewModel.establishmentCard
            )
            InfoBar(establishmentCard = viewModel.establishmentCard)
            BudleInfoTagList(tags = viewModel.establishmentCard.tags)
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                viewModel.establishmentCard.description?.let {
                    EstablishmentCardDescription(
                        description = it
                    )
                }
                viewModel.establishmentCard.workingHours?.let { WorkingTime(cardDescription = it.toList()) }
                EstablishmentAddress(addressInfo = viewModel.establishmentCard.address)
                EstablishmentCardPhoto(establishment = viewModel.establishmentCard,
                    onClick = { viewModel.clickedGallery.value = !viewModel.clickedGallery.value })
            }
        }
    }
}

@Composable
fun EstablishmentBanner(
    establishmentCard: Establishment,
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
                painter = it,
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
                    establishmentCard.starsCount?.let {
                        Text(
                            text = "$it звезд",
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
    establishmentCard: Establishment?,
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
                BudlePhotoTag(
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
    description: String,
) {
    BudleBlockWithHeader(headerText = "Описание") {
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
    cardDescription: List<WorkingHour>,
) {
    BudleBlockWithHeader(headerText = "Время работы") {
        Column {
            for (i in cardDescription.indices) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
                    // FIXME: исправьте хардкод ПН
                    Text(
                        modifier = Modifier.width(50.dp),
                        text = "ПН",
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = cardDescription[i].startTime.dropLast(3) + "-" + cardDescription[i].endTime.dropLast(
                            3
                        ),
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
    addressInfo: String,
) {
    BudleBlockWithHeader(
        modifier = Modifier.padding(bottom = 10.dp), headerText = "Адрес"
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

@Composable
fun ShowPhotoGallery(
    establishment: Establishment?,
    isClicked: MutableState<Boolean>,
) {

    val configuration = LocalConfiguration.current
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    val screenWidth = configuration.screenWidthDp.dp - 60.dp
    var currentIndex by remember { mutableStateOf(0) }

    if (isClicked.value) {
        Column(
            Modifier
                .fillMaxSize()
                .background(mainBlack.copy(0.5f))
                .zIndex(20f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                BudleIconButton(
                    modifier = Modifier.size(26.dp),
                    iconDescription = "Close",
                    iconId = R.drawable.x,
                    onClick = {
                        isClicked.value = false
                    })
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Box(
                        modifier = Modifier.height(screenWidth),
                        contentAlignment = Alignment.Center
                    ) {
                        establishment?.photos?.get(currentIndex)?.let {
                            Image(
                                painter = it,
                                contentDescription = "Restaurant Card",
                                modifier = Modifier.width(screenWidth),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                        Box(
                            Modifier
                                .matchParentSize()
                                .background(gradient)
                        )
                    }
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (establishment != null) {
                        itemsIndexed(establishment.photos) { i, photo ->
                            val selectedColor =
                                if (currentIndex == i) fillPurple else Transparent
                            val padding =
                                if (i == (establishment.photos.size.minus(1))) 0.dp else 10.dp
                            Card(
                                modifier = Modifier
                                    .padding(end = padding)
                                    .clickable(
                                        onClick = {
                                            currentIndex = i
                                        }
                                    ),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(2.dp, selectedColor)
                            ) {
                                Box(
                                    modifier = Modifier.height(60.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (photo != null) {
                                        Image(
                                            painter = photo,
                                            contentDescription = "Restaurant Card",
                                            modifier = Modifier.width(60.dp),
                                            contentScale = ContentScale.FillBounds
                                        )
                                    }
                                    Box(
                                        Modifier
                                            .matchParentSize()
                                            .background(gradient)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EstablishmentCardPhoto(
    onClick: () -> Unit = {},
    establishment: Establishment,
) {
    val photos = establishment.photos
    val restPhotos = photos.size - 6
    val mainPhotos = if (restPhotos > 0) 6 else photos.size
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))

    BudleBlockWithHeader(headerText = "Фотографии") {
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            for (i in 0..mainPhotos / 4) {
                LazyRow(
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    val rowId = i
                    val slice = if (mainPhotos >= 3 * i + 3) {
                        photos.subList(3 * i, 3 * i + 3)
                    } else photos.subList(3 * i, photos.size)
                    itemsIndexed(slice) { i, current ->
                        Card(
                            modifier = Modifier.padding(end = 10.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Box(
                                modifier = Modifier.height(90.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                if (current != null) {
                                    Image(
                                        painter = current,
                                        contentDescription = "Restaurant Card",
                                        modifier = Modifier
                                            .width(90.dp)
                                            .clickable(
                                                onClick = onClick
                                            ),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Box(
                                    Modifier
                                        .matchParentSize()
                                        .background(gradient)
                                )
                                if (rowId == 1 && i == 2 && restPhotos > 0) {
                                    Text(
                                        textAlign = TextAlign.Center,
                                        text = "+$restPhotos",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = mainWhite
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
