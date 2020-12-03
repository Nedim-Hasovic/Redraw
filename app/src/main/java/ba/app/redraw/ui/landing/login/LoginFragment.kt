package ba.app.redraw.ui.landing.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ba.app.redraw.ui.base.SimpleNavigationAction
import ba.app.redraw.ui.base.di.viewmodel.ViewModelKey
import ba.app.redraw.ui.base.view.BaseBoundFragment
import ba.app.redraw.ui.main.MainActivity
import ba.app.redraw.BR
import ba.app.redraw.R
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class LoginFragment : BaseBoundFragment<LoginViewModel>() {
    override val layoutRId: Int
        get() = R.layout.fragment_login
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    override fun bindToViewModel() {
        viewModel.navigationTrigger.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    SimpleNavigationAction.NEXT -> {
                        activity?.startActivity(Intent(activity, MainActivity::class.java))
                        activity?.finish()
                    }
                    SimpleNavigationAction.BACK -> activity?.onBackPressed()
                }
            }
        )

        viewModel.toastMessage.observe(
            viewLifecycleOwner,
            Observer {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}
