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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = newsItem.category,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = newsItem.publishedDate,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
