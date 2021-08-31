package eu.vmpay.weather.compose.app.repository.remote

import eu.vmpay.weather.compose.app.models.ItemDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PicsumService {

    @GET("v2/list?page=2&limit=100")
    suspend fun getPictureList(): List<ItemDetailsModel>

    @GET("id/{id}/info")
    suspend fun getPictureDetails(@Path("id") id: String): ItemDetailsModel
}
