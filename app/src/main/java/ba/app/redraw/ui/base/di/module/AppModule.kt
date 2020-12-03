package ba.app.redraw.ui.base.di.module

import android.app.Application
import android.content.Context
import ba.app.redraw.App
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * This module should provide high level, application specific dependencies
 */
@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideApplication(app: App): Application

    @Binds
    @Singleton
    abstract fun provideContext(application: Application): Context
}
