package ba.app.redraw.ui.base.di.module

import ba.app.redraw.ui.base.di.ActivityScope
import ba.app.redraw.ui.landing.LandingActivity
import ba.app.redraw.ui.landing.LandingFragmentBuilder
import ba.app.redraw.ui.landing.LandingHostModule
import ba.app.redraw.ui.main.MainActivity
import ba.app.redraw.ui.main.MainFragmentBuilder
import ba.app.redraw.ui.main.MainModule
import ba.app.redraw.ui.splash.SplashActivity
import ba.app.redraw.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activities and their components should be provided in this file
 */
@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun provideSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LandingHostModule::class, LandingFragmentBuilder::class])
    abstract fun provideLandingActivity(): LandingActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainFragmentBuilder::class])
    abstract fun provideMainActivity(): MainActivity
}
