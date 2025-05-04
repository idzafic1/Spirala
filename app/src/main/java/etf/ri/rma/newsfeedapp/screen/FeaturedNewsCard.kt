package etf.ri.rma.newsfeedapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import etf.ri.rma.newsfeedapp.R
import etf.ri.rma.newsfeedapp.model.NewsItem

@Composable
fun FeaturedNewsCard(newsItem: NewsItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            newsItem.imageUrl?.let { imageUrl ->
                val resourceName = imageUrl.substringAfter("drawable/").substringBefore(".")
                val resourceId = LocalContext.current.resources.getIdentifier(
                    resourceName, "drawable", LocalContext.current.packageName
                )

                if (resourceId != 0) {
                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = "Featured news image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Text(
                text = newsItem.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = newsItem.snippet,
                style = MaterialTheme.typography.bodyLarge
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



@Preview(showBackground = true)
@Composable
fun PreviewFeaturedNewsCard() {
    FeaturedNewsCard(
        newsItem = NewsItem(
            id = "1",
            title = "Nasa new moon mission launches successfully",
            snippet = "NASA's Artemis I rocket has launched for its mission to the moon, marking a new chapter in lunar exploration.",
            category = "Nauka/tehnologija",
            source = "NASA",
            publishedDate = "04-05-2025",
            isFeatured = true,
            imageUrl = "drawable/image.jpg"
        )
    )
}