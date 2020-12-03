package ba.app.redraw.ui.landing

import ba.app.redraw.ui.base.di.FragmentScope
import ba.app.redraw.ui.base.view.BaseActivity
import ba.app.redraw.ui.landing.login.LoginFragment
import ba.app.redraw.ui.landing.login.LoginModule
import ba.app.redraw.R
import dagger.Module
import dagger.android.ContributesAndroidInjector

class LandingActivity : BaseActivity() {
    override val layoutRId: Int
        get() = R.layout.activity_landing
}

@Module
abstract class LandingHostModule

@Module
abstract class LandingFragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector(modules = [LandingModule::class])
    abstract fun provideLandingFragment(): LandingFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun provideLoginFragment(): LoginFragment
}
