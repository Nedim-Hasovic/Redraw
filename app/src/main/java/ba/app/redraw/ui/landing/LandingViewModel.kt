package ba.app.redraw.ui.landing

import android.view.View
import ba.app.redraw.scheduling.DispatcherProvider
import ba.app.redraw.ui.base.viewmodel.BaseViewModel
import ba.app.redraw.ui.base.viewmodel.SingleLiveEvent
import javax.inject.Inject

class LandingViewModel
@Inject constructor(dispatcherProvider: DispatcherProvider) : BaseViewModel(dispatcherProvider) {
    enum class NavigationEvent {
        LOGIN
    }

    val navigationTrigger = SingleLiveEvent<NavigationEvent>()

    fun loginClick(view: View) {
        navigationTrigger.value = NavigationEvent.LOGIN
    }
}
