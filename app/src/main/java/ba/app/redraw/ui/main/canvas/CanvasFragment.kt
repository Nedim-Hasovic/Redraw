package ba.app.redraw.ui.main.canvas

import androidx.lifecycle.ViewModel
import ba.app.redraw.BR
import ba.app.redraw.R
import ba.app.redraw.ui.base.di.viewmodel.ViewModelKey
import ba.app.redraw.ui.base.view.BaseBoundFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class CanvasFragment : BaseBoundFragment<CanvasViewModel>() {
    override val layoutRId: Int
        get() = R.layout.fragment_canvas
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<CanvasViewModel>
        get() = CanvasViewModel::class.java

    override fun bindToViewModel() {
    }
}

@Module
abstract class CanvasModule {

    @Binds
    @IntoMap
    @ViewModelKey(CanvasViewModel::class)
    abstract fun provideCanvasViewModel(canvasViewModel: CanvasViewModel): ViewModel
}
