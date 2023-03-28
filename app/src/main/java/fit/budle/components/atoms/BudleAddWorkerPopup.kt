package fit.budle.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import fit.budle.R
import fit.budle.components.atoms.inputs.text_inputs.BudleNumberInput
import fit.budle.components.data.NumberDefaults
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleAddWorkerPopup(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(10f)
            .background(mainBlack.copy(0.5f)),
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
                        onClick = onClick
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
                    mask = NumberDefaults.MASK
                )
                BudleButton(
                    topPadding = 15.dp,
                    horizontalPadding = 0.dp,
                    onClick = onClick,
                    buttonText = "Отправить приглашение",
                    disabledButtonColor = fillPurple,
                    disabledTextColor = mainWhite
                )
            }
        }
    }
}
