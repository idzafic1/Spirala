package etf.ri.rma.newsfeedapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import etf.ri.rma.newsfeedapp.R
import etf.ri.rma.newsfeedapp.model.NewsItem



@Composable
fun FeaturedNewsCard(newsItem: NewsItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = newsItem.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 2
            )
            Text(
                text = newsItem.snippet,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 2
            )
            Text(
                text = "${newsItem.source} • ${newsItem.publishedDate}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1
            )
        }
    }
}