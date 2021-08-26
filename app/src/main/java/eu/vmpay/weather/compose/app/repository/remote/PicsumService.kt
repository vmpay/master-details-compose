package eu.vmpay.weather.compose.app.repository.remote

import eu.vmpay.weather.compose.app.models.ItemDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PicsumService {

    @GET("id/{id}/info")
    suspend fun getPictureDetails(@Path("id") id: String): ItemDetailsModel
}
