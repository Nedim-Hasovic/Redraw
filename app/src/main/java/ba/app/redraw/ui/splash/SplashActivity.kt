package ba.app.redraw.ui.splash

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import ba.app.redraw.R
import ba.app.redraw.data.session.SessionRepository
import ba.app.redraw.scheduling.DispatcherProvider
import ba.app.redraw.ui.base.di.viewmodel.ViewModelKey
import ba.app.redraw.ui.base.view.BaseBoundActivity
import ba.app.redraw.ui.base.viewmodel.BaseViewModel
import ba.app.redraw.ui.base.viewmodel.SingleLiveEvent
import ba.app.redraw.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

class SplashActivity : BaseBoundActivity<SplashViewModel>() {
    override val layoutRId: Int
        get() = 0
    override val viewModelNameRId: Int
        get() = 0
    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    override fun bindToViewModel() {
        viewModel.navigationEvent.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

class SplashViewModel
@Inject constructor(
        private val sessionRepository: SessionRepository,
        dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    enum class NavigationEvent {
        MAIN,
        LANDING
    }

    val navigationEvent = SingleLiveEvent<NavigationEvent>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStart() {
        runIo {
            sessionRepository.hasSession().also {
                // TODO: will be implemented later
                navigationEvent.postValue(NavigationEvent.MAIN)
            }
        }
    }
}

@Module
abstract class SplashModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun provideSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}
