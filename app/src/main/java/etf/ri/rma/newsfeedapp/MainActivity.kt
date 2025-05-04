
package etf.ri.rma.newsfeedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import etf.ri.rma.newsfeedapp.Navigation.NewsFeedNavigation
import etf.ri.rma.newsfeedapp.screen.FilterScreen
import etf.ri.rma.newsfeedapp.screen.NewsFeedScreen
import etf.ri.rma.newsfeedapp.ui.theme.SpiralaCopyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpiralaCopyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsFeedNavigation()
                }
            }
        }
    }
}
