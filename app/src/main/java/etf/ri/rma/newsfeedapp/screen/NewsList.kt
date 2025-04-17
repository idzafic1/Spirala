package etf.ri.rma.newsfeedapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import etf.ri.rma.newsfeedapp.model.NewsItem
import androidx.compose.material3.Text

@Composable
fun NewsList(newsItems: List<NewsItem>, selectCategories: List<String>) {
    var selectedID by remember() { mutableStateOf(setOf<String>()) }
    val filteredNewsItems = if (selectCategories.contains("Sve")) {
        newsItems
    } else {
        newsItems.filter { it.category in selectCategories }
    }

    if (filteredNewsItems.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        ) {
            Text(
                text = "No news available",
                modifier = Modifier.testTag("no_news_text")
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.testTag("news_list")
        ) {
            items(filteredNewsItems) { newsItem ->
                if (newsItem.isFeatured) {
                    FeaturedNewsCard(newsItem = newsItem,
                        isSelecterd = selectedID.contains(newsItem.id),
                        onClick = {
                            selectedID = if (selectedID.contains(newsItem.id)) {
                                selectedID - newsItem.id
                            } else {
                                selectedID + newsItem.id
                            }
                        }
                    )
                } else {
                    StandardNewsCard(newsItem = newsItem,
                        selected = selectedID.contains(newsItem.id),
                        onClick = {
                            selectedID = if (selectedID.contains(newsItem.id)) {
                                selectedID - newsItem.id
                            } else {
                                selectedID + newsItem.id
                            }
                        }
                    )
                }
            }
        }
    }
}