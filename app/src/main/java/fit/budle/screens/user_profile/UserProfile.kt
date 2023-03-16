package fit.budle.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.navigation.NavRoute
import fit.budle.models.Tab
import fit.budle.models.tabs
import fit.budle.ui.theme.*

@Composable
fun UserProfileScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            ShowHeader(navController)
            ShowColumn(navController)
            ShowButton(navController)
            ShowBudlePattern()
        }
    }
}

@Composable
fun ShowHeader(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier.size(40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lightBlue
                ),
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent),
                    contentAlignment = Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "User icon",
                        tint = textGray
                    )
                }
            }
            Text(
                text = "Артём Третьяков",
                modifier = Modifier.padding(start = 20.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }
        IconButton(
            onClick = {
                navController.navigate(route = NavRoute.MainList.route)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.x),
                contentDescription = "Close app",
                tint = mainBlack
            )
        }
    }
}

@Composable
fun ShowColumn(navController: NavHostController) {
    val institutionStates = remember { tabs }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        itemsIndexed(institutionStates) { i, _ ->
            Divider(thickness = 1.dp, color = lightBlue)
            ShowTab(navController = navController, tab = institutionStates[i])
        }
    }
}

@Composable
fun ShowTab(
    navController: NavHostController,
    tab: Tab
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 25.dp)
            .clickable(
                onClick = {
                    navController.navigate(route = tab.route)
                }
            )
    ) {
        Icon(
            painter = painterResource(id = tab.iconID),
            contentDescription = tab.text,
            tint = tab.color ?: textGray
        )
        Text(
            text = tab.text,
            modifier = Modifier.padding(start = 15.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ShowButton(navController: NavHostController) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonColor = if (isPressed) fillPurple else lightBlue
    val contentColor = if (isPressed) mainWhite else textGray

    Button(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            navController.navigate(route = NavRoute.UserProfileSettings.route)
        },
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(buttonColor)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .width(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.zap),
                contentDescription = "Button icon",
                tint = contentColor
            )
            Text(
                text = "Создать бизнес-аккаунт",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor
            )
        }
    }
}

@Composable
fun ShowBudlePattern() {
    Box(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.align(Center),
            imageVector = ImageVector.vectorResource(id = R.drawable.budle_pattern),
            contentDescription = "Budle pattern"
        )
    }
}