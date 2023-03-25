package fit.budle.screens.customer.establishments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.*
import fit.budle.components.atoms.tags.BudleRateTag
import fit.budle.components.atoms.tags.BudleRectangleTag
import fit.budle.components.moleculas.BudleBlockWithHeader
import fit.budle.components.moleculas.budleTagList
import fit.budle.models.EstablishmentCard
import fit.budle.models.RectangleTag
import fit.budle.navigation.navigate
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.*

@RequiresApi(Build.VERSION_CODES.N)
@Suppress("UNCHECKED_CAST")
@Composable
fun EstablishmentCardScreen(
    navHostController: NavHostController,
    establishmentCard: EstablishmentCard
) {

    val establishmentInfo = establishmentCard.establishmentCardModel.infoList
    val photos = (establishmentInfo[1] as Pair<String, MutableList<Int>>).second
    val restPhotos = if (photos.size > 6) photos.subList(6, photos.size) else photos
    val isCLicked = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ShowPhotoGallery(
            photoInfo = restPhotos,
            isClicked = isCLicked
        )
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
                BudleIconButton(
                    modifier = Modifier.size(26.dp),
                    iconDescription = "Close",
                    iconId = R.drawable.x,
                    onClick = {
                        navHostController.navigate(NavRoute.MainPage.route)
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
                        navHostController.navigate(
                            NavRoute.BookingProcess.route,
                            bundleOf("EST_KEY" to establishmentCard)
                        )
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
                establishmentCard = establishmentCard
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                InfoBar(establishmentCard = establishmentCard)
                budleTagList(
                    tagList = establishmentCard.establishmentCardModel.tags,
                    textColor = textGray
                )
                EstablishmentCardDescription(description = establishmentInfo[0] as Pair<String, String>)
                EstablishmentCardPhoto(
                    photoInfo = establishmentInfo[1] as Pair<String, MutableList<Int>>,
                    onClick = {
                        isCLicked.value = true
                    }
                )
                WorkingTime(cardDescription = establishmentInfo[2] as Pair<String, MutableMap<String, String>>)
                EstablishmentAddress(addressInfo = establishmentInfo[3] as Pair<String, MutableMap<String, String>>)
            }
        }
    }
}

@Composable
fun EstablishmentBanner(
    establishmentCard: EstablishmentCard
) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    val description = establishmentCard.establishmentCardModel.establishmentDescription
    val photos =
        (establishmentCard.establishmentCardModel.infoList[1].second as MutableList<*>).size
    Box(
        modifier = Modifier
            .padding(0.dp)
            .height(200.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painter = painterResource(
                id = description.imgID
            ),
            contentDescription = "Restaurant Card",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
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
                        text = description.type,
                        style = MaterialTheme.typography.labelSmall,
                        color = mainWhite,
                    )
                    Card(
                        modifier = Modifier.padding(horizontal = 7.dp),
                        shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier
                                .size(3.dp)
                                .background(mainWhite)
                        )
                    }
                    Text(
                        text = description.subtype,
                        style = MaterialTheme.typography.labelSmall,
                        color = mainWhite
                    )
                }
                Text(
                    text = description.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = mainWhite,
                )
            }
            BudleRateTag(
                tag = "$photos фото",
                width = 75.dp
            )
        }
    }
}

@Composable
fun InfoBar(
    establishmentCard: EstablishmentCard
) {

    val isFavorite = establishmentCard.establishmentCardModel
        .establishmentDescription.isFavorite.value

    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BudleRateTag(
                tag = establishmentCard.rate.toString(),
                color = fillPurple,
                textColor = mainWhite
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "Рейтинг",
                style = MaterialTheme.typography.bodyMedium,
                color = textGray
            )
        }
        Row(Modifier.width(IntrinsicSize.Max)) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "Share icon",
                    tint = textGray
                )
            }
            IconButton(
                onClick = {
                    establishmentCard.establishmentCardModel.establishmentDescription
                        .isFavorite.value = !isFavorite
                }
            ) {
                if (!establishmentCard.establishmentCardModel
                        .establishmentDescription
                        .isFavorite.value
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart_outline),
                        contentDescription = "Add to favorites",
                        tint = textGray
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "Added to favorites",
                        tint = backgroundError
                    )
                }
            }
        }
    }
}

@Composable
fun EstablishmentCardDescription(
    description: Pair<String, String>
) {
    BudleBlockWithHeader(headerText = description.first) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = description.second,
            style = MaterialTheme.typography.bodyMedium,
            color = mainBlack
        )
    }
}

@Composable
fun EstablishmentCardPhoto(
    onClick: () -> Unit = {},
    photoInfo: Pair<String, MutableList<Int>>
) {
    val photos = photoInfo.second
    val restPhotos = photos.size - 6
    val mainPhotos = if (restPhotos > 0) 6 else photos.size
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))

    BudleBlockWithHeader(headerText = photoInfo.first) {
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
                    itemsIndexed(slice) { i, _ ->

                        Card(
                            modifier = Modifier.padding(end = 10.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Box(
                                modifier = Modifier.height(90.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = slice[i]),
                                    contentDescription = "Restaurant Card",
                                    modifier = Modifier.width(90.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    Modifier
                                        .matchParentSize()
                                        .background(gradient)
                                )
                                if (rowId == 1 && i == 2 && restPhotos > 0) {
                                    Text(
                                        modifier = Modifier.clickable(
                                            onClick = onClick
                                        ),
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

@Composable
fun WorkingTime(
    cardDescription: Pair<String, Map<String, String>>
) {

    val table = cardDescription.second

    BudleBlockWithHeader(headerText = cardDescription.first) {
        Column {
            for (i in 0 until table.size) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
                    Text(
                        modifier = Modifier.width(50.dp),
                        text = table.keys.elementAt(i),
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = table.values.elementAt(i),
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentAddress(
    addressInfo: Pair<String, MutableMap<String, String>>
) {
    val table = addressInfo.second
    BudleBlockWithHeader(
        modifier = Modifier.padding(bottom = 100.dp),
        headerText = addressInfo.first
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
                if (table["Метро"] != null) {
                    table["Метро"]?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = mainBlack
                        )
                    }
                }
                table["Адрес"]?.let {
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                }
            }
            BudleRectangleTag(
                isSelected = { false },
                onChangeState = {},
                tag = RectangleTag(
                    tagName = "Карта",
                    tagId = R.drawable.map
                ),
                color = fillPurple
            )
        }
    }
}

@Composable
fun ShowPhotoGallery(
    photoInfo: MutableList<Int>,
    isClicked: MutableState<Boolean>
) {

    val configuration = LocalConfiguration.current
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    val screenWidth = configuration.screenWidthDp.dp - 60.dp
    val currentIndex = remember { mutableStateOf(0) }

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
                        Image(
                            painter = painterResource(id = photoInfo[currentIndex.value]),
                            contentDescription = "Restaurant Card",
                            modifier = Modifier.width(screenWidth),
                            contentScale = ContentScale.Crop
                        )
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
                    itemsIndexed(photoInfo) { i, photo ->
                        val selectedColor = if (currentIndex.value == i) fillPurple else Transparent
                        val padding = if (i == photoInfo.size - 1) 0.dp else 10.dp
                        Card(
                            modifier = Modifier
                                .padding(end = padding)
                                .clickable(
                                    onClick = {
                                        currentIndex.value = i
                                    }
                                ),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(2.dp, selectedColor)
                        ) {
                            Box(
                                modifier = Modifier.height(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = photo),
                                    contentDescription = "Restaurant Card",
                                    modifier = Modifier.width(60.dp),
                                    contentScale = ContentScale.Crop
                                )
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