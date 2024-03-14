package fit.budle.ui.screens.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fit.budle.R
import fit.budle.dto.WorkingHour
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.establishment.Review
import fit.budle.event.customer.MainEvent
import fit.budle.ui.components.BudleIconButton
import fit.budle.ui.components.BudleInfoTagList
import fit.budle.ui.components.BudlePhotoTag
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.theme.BudleTheme
import fit.budle.ui.theme.alphaBlack
import fit.budle.ui.theme.borderColor
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.navyBlue
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.MainViewModel

@Composable
fun CardScreen(
    navHostController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    viewModel.onEvent(MainEvent.GetEstablishment)

    ShowPhotoGallery(
        establishment = viewModel.establishmentCard, isClicked = viewModel.clickedGallery
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
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
            BudleInfoTagList(iconTags = viewModel.establishmentCard.iconTags)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 100.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                ) {
                    viewModel.establishmentCard.description?.let {
                        EstablishmentCardDescription(
                            description = it
                        )
                    }
                    viewModel.establishmentCard.workingHours?.let { WorkingTime(it.toList()) }
                    EstablishmentAddress(addressInfo = viewModel.establishmentCard.address)
                    EstablishmentCardPhoto(establishment = viewModel.establishmentCard,
                        onClick = {
                            viewModel.clickedGallery.value = !viewModel.clickedGallery.value
                        })
                }
                EstablishmentReviewsBlock(
                    reviews = viewModel.establishmentReviews,
                    rating = viewModel.establishmentCard.rating,
                    navHostController
                )
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
    cardTimes: List<WorkingHour>,
) {
    BudleBlockWithHeader(headerText = "Время работы") {
        Column {
            for (time in cardTimes) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
                    Text(
                        modifier = Modifier.width(50.dp),
                        text = time.dayOfWeek,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = time.startTime.dropLast(3) + "-" + time.endTime.dropLast(
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
                BudleIconButton(modifier = Modifier.size(26.dp),
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
                        modifier = Modifier.height(screenWidth), contentAlignment = Alignment.Center
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
                            val selectedColor = if (currentIndex == i) fillPurple else Transparent
                            val padding =
                                if (i == (establishment.photos.size.minus(1))) 0.dp else 10.dp
                            Card(
                                modifier = Modifier
                                    .padding(end = padding)
                                    .clickable(onClick = {
                                        currentIndex = i
                                    }),
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

@Composable
fun EstablishmentReviewsBlock(
    reviews: Array<Review>,
    rating: Double?,
    navHostController: NavController?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text(
                    text = "Отзывы",
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "(" + reviews.size.toString() + ")",
                    style = MaterialTheme.typography.titleSmall,
                    color = textGray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = fillPurple
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = rating.toString(),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            items(reviews.size) { index ->
                EstablishmentReview(review = reviews[index])
            }
        }
        if (reviews.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navHostController?.navigate("reviewsScreen")
                },
                colors = ButtonColors(
                    containerColor = lightBlue,
                    contentColor = mainBlack,
                    lightBlue,
                    lightBlue
                )
            ) {
                Text(
                    text = "Посмотреть все",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewEstablishmentReviewsBlock() {
    val reviews = arrayOf(
        Review(
            username = "Артём",
            date = "2023-02-22",
            score = 5,
            text = "Очень крутое заведение! Приду сюда ещё раз с бабушкой!"
        ),
        Review(
            username = "Олег",
            date = "2023-02-22",
            score = 3,
            text = "Очень крутое заведение! Приду сюда ещё раз с бабушкой! " +
                    "Мне правда здесь очень понравилось, приведу сюда всех своих друзей"
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BudleTheme {
            EstablishmentReviewsBlock(reviews, 4.1, null)
        }
    }

}

@Composable
fun EstablishmentReview(
    review: Review,
) {
    OutlinedCard(
        modifier = Modifier
            .width(312.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, borderColor),
        colors = CardColors(Color.White, mainBlack, Color.White, Color.White)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            StarsRating(review.score)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = review.text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = review.username,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .background(textGray, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = review.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
            }
        }
    }
}

@Composable
fun StarsRating(score: Int) {
    Row {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                tint = if (i <= score) fillPurple else navyBlue
            )
            if (i != 5)
                Spacer(modifier = Modifier.width(3.dp))
        }
    }
}
