package eu.vmpay.weather.compose.app.models

import com.google.gson.annotations.SerializedName


data class ItemDetailsModel(
    val id: String,
    val author: String,
    val width: Long,
    val height: Long,
    val url: String,
    @SerializedName("download_url")
    val downloadURL: String
)
