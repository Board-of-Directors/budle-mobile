package fit.budle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import fit.budle.ui.details.BudleTest
import fit.budle.ui.theme.BudleTheme
import fit.budle.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    val mainViewModel = MainViewModel("79231095499")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    BudleTest(mainViewModel.result)
                }
            }
        }
    }
}
