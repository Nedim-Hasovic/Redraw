package ba.app.redraw.ui.landing.login

import androidx.lifecycle.MutableLiveData
import ba.app.redraw.data.session.Credentials
import ba.app.redraw.data.session.SessionRepository
import ba.app.redraw.scheduling.DispatcherProvider
import ba.app.redraw.ui.base.SimpleNavigationAction
import ba.app.redraw.ui.base.viewmodel.BaseViewModel
import ba.app.redraw.ui.base.viewmodel.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val sessionRepository: SessionRepository,
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val navigationTrigger = SingleLiveEvent<SimpleNavigationAction>()
    val toastMessage = MutableLiveData<String>()

    fun onLoginClick() {
        runIo {
            try {
                sessionRepository.logIn(Credentials(username.value!!, password.value!!))
                navigationTrigger.postValue(SimpleNavigationAction.NEXT)
            } catch (e: Exception) {
                e.printStackTrace()
                toastMessage.postValue("Failed")
            }
        }
    }
}
