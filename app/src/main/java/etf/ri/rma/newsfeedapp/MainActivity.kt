package etf.ri.rma.newsfeedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import etf.ri.rma.newsfeedapp.data.NewsData
import etf.ri.rma.newsfeedapp.screen.NewsFeedScreen
import etf.ri.rma.newsfeedapp.ui.theme.SpiralaCopyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpiralaCopyTheme {
                var selectedCategory by remember { mutableStateOf("All") }
                val newsFeedScreen = NewsFeedScreen(newsItems = NewsData.getAllNews())

                newsFeedScreen.Display(selectedCategory) { category ->
                    selectedCategory = category
                }
            }
        }
    }
}