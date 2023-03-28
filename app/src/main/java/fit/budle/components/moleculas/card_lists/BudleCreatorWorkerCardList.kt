package fit.budle.components.moleculas.card_lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.components.moleculas.BudleCreatorWorkingCard
import fit.budle.models.Worker

@Composable
fun BudleCreatorWorkerCardList(
    modifier: Modifier = Modifier,
    workerList: MutableList<Worker>
) {

    val workerMap = remember {
        workerList.map{it to true}.toMutableStateMap()
    }

    val onValueChange: (Worker) -> Unit = {
        workerMap[it] = false
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        for (worker in workerList){
            if (workerMap[worker] != false){
                BudleCreatorWorkingCard(
                    onValueChange = onValueChange,
                    worker = worker
                )
            }
        }
    }
}