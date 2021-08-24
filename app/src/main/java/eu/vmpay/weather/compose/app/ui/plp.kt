package eu.vmpay.weather.compose.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.accompanist.coil.CoilImage
import eu.vmpay.weather.compose.app.models.ItemModel
import eu.vmpay.weather.compose.app.viewmodels.ListViewModel
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun ItemListScreenPreview() {
    ItemListSuccessState(rememberNavController(), listOf())
}

@Preview
@Composable
fun ItemListLoadingScreenPreview() {
    LoadingState()
}

@Preview
@Composable
fun ItemListEmptyScreenPreview() {
    ItemListEmptyState()
}

@Composable
fun ItemListScreen(navController: NavHostController, viewModel: ListViewModel = viewModel()) {
    viewModel.isLoading.observeAsState().value?.let {
        if (it) LoadingState()
    }
    viewModel.listLD.observeAsState().value?.let { list ->
        if (list.isNotEmpty())
            ItemListSuccessState(navController, list)
        else ItemListEmptyState()
    }
}

@Composable
fun ItemListSuccessState(navController: NavHostController, list: List<ItemModel>) {
    LazyColumn(content = {
        items(list.size, itemContent = { index ->
            ItemRowWidget(item = list[index], navController)
        })
    })
}

@Composable
fun ItemListEmptyState() {
    Text("List is empty")
}

@Composable
fun LoadingState() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun ItemRowWidget(item: ItemModel, navController: NavHostController) {
    val typography = MaterialTheme.typography
    Column(modifier = Modifier.padding(16.dp)) {
        CoilImage(
            data = item.image,
            contentDescription = item.title,
            loading = {
                LoadingState()
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
                .clickable {
                    navController.navigate("pdp/${item.id}")
                }
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Text(
            item.title,
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(item.location, style = typography.body2)
        Text(
            SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date(item.timeStamp)),
            style = typography.body2
        )
    }
}