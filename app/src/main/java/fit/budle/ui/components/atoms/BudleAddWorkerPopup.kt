package fit.budle.ui.components.atoms

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import fit.budle.R
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleNumberInput
import fit.budle.ui.models.NumberDefaults
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleAddWorkerPopup(
    onClose: () -> Unit,
    onValueChange: (String) -> Unit
) {

    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(20f)
            .background(mainBlack.copy(0.6f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(IntrinsicSize.Max),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(mainWhite)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Добавить работника",
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    IconButton(
                        onClick = onClose
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.x),
                            contentDescription = "Close",
                            tint = mainBlack
                        )
                    }
                }
                BudleNumberInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Номер телефона",
                    inputLength = NumberDefaults.INPUT_LENGTH,
                    mask = NumberDefaults.MASK,
                    onValueChange = { phoneNumber = it },
                    exceptionMessage = ""
                )
                Log.d("TEXTSTATE", phoneNumber)
                BudleButton(
                    topPadding = 15.dp,
                    horizontalPadding = 0.dp,
                    onClick = { onValueChange(phoneNumber) },
                    buttonText = "Отправить приглашение",
                    disabledButtonColor = fillPurple,
                    disabledTextColor = mainWhite
                )
            }
        }
    }
}