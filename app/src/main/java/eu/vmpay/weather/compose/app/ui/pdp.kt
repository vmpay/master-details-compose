package eu.vmpay.weather.compose.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Preview
@Composable
fun ItemDetailsScreenPreview() {
    ItemDetailsScreen("123")
}

@Composable
fun ItemDetailsScreen(id: String?) {
    val typography = MaterialTheme.typography
    Column(modifier = Modifier.padding(16.dp)) {
        CoilImage(
            data = "https://picsum.photos/id/$id/1200/600",
            contentDescription = null,
            loading = {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.notification_template_icon_bg),
                    contentDescription = null,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            },
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "ItemDetailsScreen $id",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}