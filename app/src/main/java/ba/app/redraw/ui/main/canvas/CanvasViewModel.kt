package ba.app.redraw.ui.main.canvas

import ba.app.redraw.scheduling.DispatcherProvider
import ba.app.redraw.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class CanvasViewModel @Inject constructor(
        dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider)
