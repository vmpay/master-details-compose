package eu.vmpay.weather.compose.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import eu.vmpay.weather.compose.app.models.ItemDetailsModel
import eu.vmpay.weather.compose.app.viewmodels.DetailsViewModel

@Preview
@Composable
fun ItemDetailsScreenPreview() {
    ItemDetailsScreen()
}

@Composable
fun ItemDetailsScreen(viewModel: DetailsViewModel = viewModel()) {
    with(viewModel) {
        isLoading.observeAsState().value?.let {
            if (it) LoadingState()
        }
        isError.observeAsState().value?.let {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = it)
            }
        }
        detailsLD.observeAsState().value?.let {
            DetailsSuccessState(it)
        }
    }
}

@Composable
fun DetailsSuccessState(itemDetailsModel: ItemDetailsModel) {
    val typography = MaterialTheme.typography
    Column(modifier = Modifier.padding(16.dp)) {
        CoilImage(
            data = itemDetailsModel.downloadURL,
            contentDescription = null,
            loading = {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            },
            error = {
                Image(
                    painter = painterResource(id = android.R.drawable.stat_notify_error),
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
            "Author: ${itemDetailsModel.author}",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            "ID: ${itemDetailsModel.id}",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}