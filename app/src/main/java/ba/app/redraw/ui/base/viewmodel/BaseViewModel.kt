package ba.app.redraw.ui.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.app.redraw.scheduling.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext

abstract class BaseViewModel(protected val dispatcherProvider: DispatcherProvider) : ViewModel(), LifecycleObserver {

    var colorSelected = MutableLiveData<Int>()

    protected fun runOn(coroutineDispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Unit) {
        (viewModelScope + coroutineDispatcher).launch {
            block()
        }
    }

    protected fun runIo(block: suspend CoroutineScope.() -> Unit) = runOn(dispatcherProvider.io(), block)

    protected fun runComputation(block: suspend CoroutineScope.() -> Unit) = runOn(dispatcherProvider.computation(), block)
    protected suspend fun CoroutineScope.runOnMain(block: CoroutineScope.() -> Unit) = withContext(dispatcherProvider.main()) {
        block()
    }
}
