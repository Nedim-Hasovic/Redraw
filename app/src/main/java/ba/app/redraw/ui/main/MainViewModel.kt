package ba.app.redraw.ui.main

import ba.app.redraw.data.session.SessionRepository
import ba.app.redraw.scheduling.DispatcherProvider
import ba.app.redraw.ui.base.viewmodel.BaseViewModel
import ba.app.redraw.ui.base.viewmodel.SingleLiveEvent
import javax.inject.Inject

class MainViewModel
@Inject constructor(
        private val sessionRepository: SessionRepository,
        dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    val landingNavigationTrigger = SingleLiveEvent<Void>()

    fun onLogOutClick() {
        runIo {
            sessionRepository.logOut()
            runOnMain {
                landingNavigationTrigger.call()
            }
        }
    }
}
