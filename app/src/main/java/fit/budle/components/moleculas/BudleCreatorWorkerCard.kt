package fit.budle.components.moleculas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.models.Worker
import fit.budle.models.WorkerStatus
import fit.budle.ui.theme.*

@Composable
fun BudleCreatorWorkingCard(
    modifier: Modifier = Modifier,
    onValueChange: (Worker) -> Unit,
    worker: Worker
){
    val statusColor = if (worker.status == WorkerStatus.WORK) backgroundSuccess else textGray
    Card(
        modifier = modifier
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
                        text = worker.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = worker.status.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = statusColor
                    )
                }
                IconButton(
                    onClick = {
                        onValueChange(worker)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = "Delete worker",
                        tint = fillPurple
                    )
                }
            }
        }
    }
}