package ba.app.redraw.ui.base.di.module

import ba.app.redraw.data.auth.ClientConfig
import ba.app.redraw.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppConfigModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideClientConfig(): ClientConfig = object : ClientConfig {
        override val clientId: String
            get() = BuildConfig.clientId
        override val clientSecret: String
            get() = BuildConfig.clientSecret
    }
}
