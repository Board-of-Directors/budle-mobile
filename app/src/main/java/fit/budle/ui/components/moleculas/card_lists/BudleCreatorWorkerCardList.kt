package fit.budle.ui.components.moleculas.card_lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fit.budle.dto.worker.Worker
import fit.budle.ui.components.atoms.cards.BudleCreatorWorkingCard

@Composable
fun BudleCreatorWorkerCardList(
    modifier: Modifier = Modifier,
    workerList: MutableList<Worker>,
    onValueChange: (Worker) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        for (worker in workerList) {
            BudleCreatorWorkingCard(
                onValueChange = onValueChange,
                worker = worker
            )
        }
    }
}