package etf.ri.rma.newsfeedapp.screen

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import etf.ri.rma.newsfeedapp.model.NewsItem

class NewsFeedScreen(
    private val newsItems: List<NewsItem>
) {
    @Composable
    fun Display(selectedCategory: String, onCategorySelected: (String) -> Unit) {
        Column {
            FilterChips(selectedCategory, onCategorySelected)
            NewsList(newsItems = newsItems, selectCategory = selectedCategory)
        }
    }
}

@Composable
fun FilterChips(selectedCategory: String, onCategorySelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val categories = listOf("All", "Politika", "Sport", "Nauka/tehnologija")
        val tags = listOf("filter_chip_all", "filter_chip_pol", "filter_chip_spo", "filter_chip_sci")

        categories.forEachIndexed { index, category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                modifier = Modifier.testTag(tags[index])
            )
        }
    }
}