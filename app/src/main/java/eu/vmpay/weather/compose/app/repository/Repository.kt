package eu.vmpay.weather.compose.app.repository

import eu.vmpay.weather.compose.app.models.ItemDetailsModel
import eu.vmpay.weather.compose.app.repository.remote.PicsumService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val picsumService: PicsumService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getPictureDetails(id: String): ItemDetailsModel = withContext(ioDispatcher) {
        picsumService.getPictureDetails(id)
    }
}
