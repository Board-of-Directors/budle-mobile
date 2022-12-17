package fit.budle.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fit.budle.mobile.ui.details.BudleTest
import fit.budle.mobile.ui.theme.BudleMobileTheme
import fit.budle.mobile.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    val mainViewModel = MainViewModel("79231095499")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudleMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    BudleTest(mainViewModel.result)
                }
            }
        }
    }
}
