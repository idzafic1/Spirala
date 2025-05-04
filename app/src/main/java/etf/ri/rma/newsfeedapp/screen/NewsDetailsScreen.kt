package etf.ri.rma.newsfeedapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import etf.ri.rma.newsfeedapp.data.NewsData
import etf.ri.rma.newsfeedapp.model.NewsItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import etf.ri.rma.newsfeedapp.ui.theme.SpiralaCopyTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.activity.compose.BackHandler

@Composable
fun NewsDetailsScreen(newsId: String, navController: NavController) {
    val allNews = NewsData.getAllNews()
    val currentNews = allNews.find { it.id == newsId }

    if (currentNews == null) {
        Text("Vijest nije pronađena")
        return
    }

    val relatedNews = findRelatedNews(currentNews, allNews)
    val scrollState = rememberScrollState()

    BackHandler {
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = currentNews.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("details_title")
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = currentNews.snippet,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.testTag("details_snippet")
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(
                text = "Kategorija: ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = currentNews.category,
                modifier = Modifier.testTag("details_category")
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = "Izvor: ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = currentNews.source,
                modifier = Modifier.testTag("details_source")
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = "Datum objave: ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = currentNews.publishedDate,
                modifier = Modifier.testTag("details_date")
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Povezane vijesti iz iste kategorije:",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (relatedNews.size >= 1) {
            Text(
                text = relatedNews[0].title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("details/${relatedNews[0].id}") {
                            popUpTo("home")
                        }
                    }
                    .testTag("related_news_title_1")
            )
        }

        if (relatedNews.size >= 2) {
            Text(
                text = relatedNews[1].title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("details/${relatedNews[1].id}") {
                            popUpTo("home")
                        }
                    }
                    .testTag("related_news_title_2")
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("details_close_button")
        ) {
            Text("Zatvori detalje")
        }
    }
}

private fun findRelatedNews(currentNews: NewsItem, allNews: List<NewsItem>): List<NewsItem> {
    val sameCategory = allNews.filter {
        it.id != currentNews.id && it.category == currentNews.category
    }

    if (sameCategory.isEmpty()) return emptyList()

    fun calculateDateDifference(date1: String, date2: String): Int {
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val d1 = formatter.parse(date1)?.time ?: 0
            val d2 = formatter.parse(date2)?.time ?: 0
            return abs((d1 - d2) / (24 * 60 * 60 * 1000)).toInt()
    }
    return sameCategory
        .sortedBy { it.title }
        .sortedBy { calculateDateDifference(it.publishedDate, currentNews.publishedDate) }
        .take(2)
}