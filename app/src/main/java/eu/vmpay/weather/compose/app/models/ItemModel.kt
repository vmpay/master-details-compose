package eu.vmpay.weather.compose.app.models

data class ItemModel(
    val id: String,
    val title: String,
    val image: String,
    val location: String,
    val timeStamp: Long
)
