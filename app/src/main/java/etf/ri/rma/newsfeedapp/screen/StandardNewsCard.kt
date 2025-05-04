package etf.ri.rma.newsfeedapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import etf.ri.rma.newsfeedapp.R
import etf.ri.rma.newsfeedapp.model.NewsItem

@Composable
fun StandardNewsCard(newsItem: NewsItem, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            newsItem.imageUrl?.let { imageUrl ->
                val resourceName = imageUrl.substringAfter("drawable/").substringBefore(".")
                val resourceId = LocalContext.current.resources.getIdentifier(
                    resourceName, "drawable", LocalContext.current.packageName
                )

                if (resourceId != 0) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .weight(0.3f)
                    ) {
                        Image(
                            painter = painterResource(id = resourceId),
                            contentDescription = "News image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Content Column
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = newsItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = newsItem.snippet,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${newsItem.source} • ${newsItem.publishedDate}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStandardNewsCard() {
    StandardNewsCard(
        newsItem = NewsItem(
            id = "1",
            title = "Otkriven novi planet u Sunčevom sustavu",
            snippet = "Astronomi su otkrili novi planet skriven iza orbite Neptuna, što bi moglo promijeniti naše razumijevanje svemira.",
            category = "Nauka/tehnologija",
            source = "Astronomy News",
            publishedDate = "04-05-2025",
            isFeatured = false,
            imageUrl = "drawable/image.jpg"
        )
    )
}