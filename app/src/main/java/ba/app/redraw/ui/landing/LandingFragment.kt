package ba.app.redraw.ui.landing

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import ba.app.redraw.ui.base.di.viewmodel.ViewModelKey
import ba.app.redraw.ui.base.view.BaseBoundFragment
import ba.app.redraw.BR
import ba.app.redraw.R
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class LandingFragment : BaseBoundFragment<LandingViewModel>() {
    override val layoutRId: Int
        get() = R.layout.fragment_landing
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<LandingViewModel>
        get() = LandingViewModel::class.java

    override fun bindToViewModel() {
        viewModel.navigationTrigger.observe(
                viewLifecycleOwner,
                {
                    when (it) {
                        LandingViewModel.NavigationEvent.LOGIN -> NavHostFragment.findNavController(this).navigate(R.id.action_landing_to_login)
                        null -> {
                        }
                    }
                }
        )
    }
}

@Module
abstract class LandingModule {

    @Binds
    @IntoMap
    @ViewModelKey(LandingViewModel::class)
    abstract fun provideLandingViewModel(landingViewModel: LandingViewModel): ViewModel
}
