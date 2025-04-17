package etf.ri.rma.newsfeedapp.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import etf.ri.rma.newsfeedapp.data.NewsData
import etf.ri.rma.newsfeedapp.screen.NewsList
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsFeedScreen() {
    var selectedCategory by remember { mutableStateOf(listOf<String>("Sve")) }
    var filteredNewsItems by remember { mutableStateOf(NewsData.getAllNews()) }
    var sortSource by remember { mutableStateOf<String?>(null) }

    Column {
        FilterChips(
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                selectedCategory = if (selectedCategory.contains(category)) {
                    val updatedSelection = selectedCategory - category
                    if (updatedSelection.isEmpty()) listOf("Sve") else updatedSelection
                } else {
                    val updatedSelection = if (category == "Sve") {
                        listOf("Sve")
                    } else {
                        (selectedCategory - "Sve") + category
                    }
                    updatedSelection
                }
            }
        )

        Spacer(modifier = Modifier.height((16.dp)))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            val options = listOf("Izvor A-Z", "Izvor Z-A")

            options.forEach { option ->
                FilterChip(
                    selected = sortSource == option,
                    onClick = {
                        sortSource = if (sortSource == option) null else option

                        filteredNewsItems = when (sortSource) {
                            "Izvor A-Z" -> filteredNewsItems.sortedBy { it.source }
                            "Izvor Z-A" -> filteredNewsItems.sortedByDescending { it.source }
                            else -> NewsData.getAllNews().filter { newsItem ->
                                selectedCategory.contains("Sve") || selectedCategory.contains(newsItem.category)
                            }
                        }
                    },
                    label = { Text(option) },
                    modifier = Modifier.testTag(when(option) {
                        "Izvor A-Z" -> "filter_chip_source_asc"
                        "Izvor Z-A" -> "filter_chip_source_desc"
                        else -> "filter_chip_source_none"
                    })
                )
            }
        }

        NewsList(
            newsItems = filteredNewsItems,
            selectCategories = selectedCategory
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterChips(selectedCategory: List<String>, onCategorySelected: (String) -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 32.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val categories = listOf("Sve", "Politika", "Sport", "Nauka/tehnologija", "None")
        val tags = listOf("filter_chip_all", "filter_chip_pol", "filter_chip_spo", "filter_chip_sci", "filter_chip_none")

        categories.forEachIndexed { index, category ->
            FilterChip(
                selected = selectedCategory.contains(category),
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                modifier = Modifier.testTag(tags[index])
            )
        }
    }
}












