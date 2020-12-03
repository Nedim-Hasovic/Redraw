package ba.app.redraw.scheduling

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Provides Android [DispatcherProvider]
 */
@Module
object AndroidSchedulingModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideAndroidSchedulingProvider(): DispatcherProvider = object : DispatcherProvider {
        override fun io(): CoroutineDispatcher = Dispatchers.IO

        override fun main(): CoroutineDispatcher = Dispatchers.Main

        override fun computation(): CoroutineDispatcher = Dispatchers.Default

        override fun single(): CoroutineDispatcher = Dispatchers.Unconfined
    }
}
