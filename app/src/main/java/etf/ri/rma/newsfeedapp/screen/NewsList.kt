package etf.ri.rma.newsfeedapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import etf.ri.rma.newsfeedapp.model.NewsItem

@Composable
fun NewsList(newsItems: List<NewsItem>, selectCategory: String) {
    val filteredNewsItems = when (selectCategory) {
        "All" -> newsItems
        else -> newsItems.filter { it.category == selectCategory }
    }

    if (filteredNewsItems.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .testTag("empty_list")
        ) {
            MessageCard(selectCategory)
        }
    } else {
        LazyColumn(
            modifier = Modifier.testTag("news_list")
        ) {
            items(filteredNewsItems) { newsItem ->
                if (newsItem.isFeatured) {
                    FeaturedNewsCard(newsItem = newsItem)
                } else {
                    StandardNewsCard(newsItem = newsItem)
                }
            }
        }
    }
}