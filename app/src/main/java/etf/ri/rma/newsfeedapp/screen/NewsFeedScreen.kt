package etf.ri.rma.newsfeedapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import etf.ri.rma.newsfeedapp.data.NewsData
import etf.ri.rma.newsfeedapp.model.NewsItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewsFeedScreen(navController: NavController) {
    // Get filter state from savedStateHandle or use defaults
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    var selectedCategory by remember {
        mutableStateOf(savedStateHandle?.get<String>("selectedCategory") ?: "Sve")
    }
    var startDate by remember {
        mutableStateOf(savedStateHandle?.get<Long>("startDate"))
    }
    var endDate by remember {
        mutableStateOf(savedStateHandle?.get<Long>("endDate"))
    }
    var unwantedWords by remember {
        mutableStateOf(savedStateHandle?.get<List<String>>("unwantedWords") ?: emptyList())
    }

    // Save filter state whenever it changes
    LaunchedEffect(selectedCategory, startDate, endDate, unwantedWords) {
        savedStateHandle?.set("selectedCategory", selectedCategory)
        savedStateHandle?.set("startDate", startDate)
        savedStateHandle?.set("endDate", endDate)
        savedStateHandle?.set("unwantedWords", unwantedWords)
    }

    // Listen for updates from filter screen
    LaunchedEffect(key1 = navController.currentBackStackEntry) {
        navController.currentBackStackEntry?.savedStateHandle?.apply {
            get<String>("updatedCategory")?.let {
                selectedCategory = it
                remove<String>("updatedCategory")
            }
            get<Long?>("updatedStartDate")?.let {
                startDate = it
                remove<Long?>("updatedStartDate")
            }
            get<Long?>("updatedEndDate")?.let {
                endDate = it
                remove<Long?>("updatedEndDate")
            }
            get<List<String>>("updatedUnwantedWords")?.let {
                unwantedWords = it
                remove<List<String>>("updatedUnwantedWords")
            }
        }
    }

    val newsItems = NewsData.getAllNews()

    // Filter news based on selection criteria
    val filteredNews = newsItems.filter { newsItem ->
        // Category filter
        val matchesCategory = selectedCategory == "Sve" || newsItem.category == selectedCategory

        // Date range filter
        val matchesDateRange = if (startDate != null && endDate != null) {
            try {
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val itemDate = dateFormat.parse(newsItem.publishedDate)?.time ?: 0
                itemDate in startDate!!..endDate!!
            } catch (e: Exception) {
                true // If date parsing fails, include the item
            }
        } else {
            true // No date filter applied
        }

        // Unwanted words filter
        val containsUnwantedWord = unwantedWords.any { word ->
            newsItem.title.contains(word, ignoreCase = true) ||
                    newsItem.snippet.contains(word, ignoreCase = true)
        }

        matchesCategory && matchesDateRange && !containsUnwantedWord
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val categories = listOf("Sve", "Politika", "Sport", "Nauka/tehnologija",  "None", "Više filtera ...")
            val tags = listOf("filter_chip_all", "filter_chip_pol", "filter_chip_spo", "filter_chip_sci", "filter_chip_none", "filter_chip_more")

            categories.forEachIndexed { index, category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = {
                        if (category == "Više filtera ...") {
                            navController.navigate("filters")
                        } else {
                            selectedCategory = category
                            savedStateHandle?.set("selectedCategory", category)
                        }
                    },
                    label = { Text(category) },
                    modifier = Modifier.testTag(tags[index])
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("news_list"),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            if (filteredNews.isEmpty()) {
                item {
                    MessageCard(selectedCategory)
                }
            } else {
                items(filteredNews) { newsItem ->
                    if (newsItem.isFeatured) {
                        FeaturedNewsCard(
                            newsItem = newsItem,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { navController.navigate("details/${newsItem.id}") }
                        )
                    } else {
                        StandardNewsCard(
                            newsItem = newsItem,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { navController.navigate("details/${newsItem.id}") }
                        )
                    }
                }
            }
        }
    }
}