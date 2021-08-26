package eu.vmpay.weather.compose.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.vmpay.weather.compose.app.repository.Repository
import eu.vmpay.weather.compose.app.repository.remote.PicsumService
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun bindRepository(
        picsumService: PicsumService,
        ioDispatcher: CoroutineDispatcher
    ): Repository = Repository(picsumService, ioDispatcher)
}
