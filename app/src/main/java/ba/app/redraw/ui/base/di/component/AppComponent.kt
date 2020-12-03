package ba.app.redraw.ui.base.di.component

import ba.app.redraw.App
import ba.app.redraw.data.auth.oauth2.OAuth2Module
import ba.app.redraw.data.base.di.module.CacheModule
import ba.app.redraw.data.base.di.module.NetworkModule
import ba.app.redraw.data.session.SessionModule
import ba.app.redraw.data.user.UserDataModule
import ba.app.redraw.scheduling.AndroidSchedulingModule
import ba.app.redraw.ui.base.di.module.ActivityBuilder
import ba.app.redraw.ui.base.di.module.AppConfigModule
import ba.app.redraw.ui.base.di.module.AppModule
import ba.app.redraw.ui.base.di.viewmodel.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        // Android
        AndroidSupportInjectionModule::class,
        AndroidSchedulingModule::class,

        // Application
        AppModule::class,
        ActivityBuilder::class,
        ViewModelFactoryModule::class,

        // Config
        AppConfigModule::class,

        // Data
        NetworkModule::class,
        CacheModule::class,
        OAuth2Module::class,
        SessionModule::class,
        UserDataModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
