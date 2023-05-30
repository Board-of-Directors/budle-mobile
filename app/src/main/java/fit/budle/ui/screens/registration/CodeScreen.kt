package fit.budle.ui.screens.registration

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.customer.RegistrationEvent
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.BudleTimerButton
import fit.budle.ui.components.atoms.inputs.text_fields.BudleCodeTextField
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.RegistrationViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun CodeScreen(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {
    val focusManager = LocalFocusManager.current
    val firstInput = 0
    val lastInput = 3
    val errorState = remember { mutableStateOf(false) }

    val maxTicks = 30
    var ticks by remember { mutableStateOf(maxTicks) }
    var run by remember { mutableStateOf(true) }
    if (ticks == 0) {
        run = false
    }
    LaunchedEffect(key1 = run) {
        while (run) {
            delay(1.seconds)
            ticks--
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 64.dp)
            ) {
                IconButton(
                    onClick = {
                        navHostController.popBackStack()
                        viewModel.requestException = ""
                    },
                    modifier = Modifier.padding(end = 40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_gray),
                        contentDescription = "Arrow Left",
                        tint = textGray
                    )
                }
                Image(
                    painter = painterResource(
                        id = R.drawable.logo_big
                    ), contentDescription = "Logo", modifier = Modifier.width(148.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 60.dp),
            ) {
                Text(
                    text = "Подтверждение номера",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "На Ваш телефон придёт СМС.\n" + "Введите его в поле ниже.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 60.dp),
                    textAlign = TextAlign.Center
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(viewModel.states) { i, _ ->
                        BudleCodeTextField(
                            states = viewModel.states,
                            i = i,
                            firstInput = firstInput,
                            lastInput = lastInput,
                            errorState = errorState.value,
                            focusManager = focusManager
                        )
                    }
                }
                if (viewModel.requestException.isNotEmpty()) {
                    Text(
                        text = viewModel.requestException,
                        style = MaterialTheme.typography.bodyMedium,
                        color = backgroundError,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 15.dp)
                            .fillMaxWidth()
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            BudleTimerButton(
                curTicks = ticks, onClick = {
                    if (!run) {
                        ticks = maxTicks
                        run = true
                        viewModel.onEvent(RegistrationEvent.GetCode)
                    }
                }
            )
            BudleButton(
                onClick = {
                    errorState.value = viewModel.states.contains("")
                    if (!errorState.value) {
                        viewModel.onEvent(RegistrationEvent.PostCode)
                        Handler(Looper.getMainLooper()).postDelayed({
                            if (viewModel.requestException.isEmpty()) {
                                navHostController.navigate("dataScreen")
                            } else {
                                for (i in 0..3) {
                                    viewModel.states[i] = ""
                                }
                            }
                        }, 1000)
                    }
                },
                buttonText = "Подтвердить"
            )
        }
    }
}
