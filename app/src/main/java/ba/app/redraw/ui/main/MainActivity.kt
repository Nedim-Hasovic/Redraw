package ba.app.redraw.ui.main

import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModel
import ba.app.redraw.BR
import ba.app.redraw.R
import ba.app.redraw.ui.base.di.FragmentScope
import ba.app.redraw.ui.base.di.viewmodel.ViewModelKey
import ba.app.redraw.ui.base.view.BaseBoundActivity
import ba.app.redraw.ui.landing.LandingActivity
import ba.app.redraw.ui.main.canvas.CanvasFragment
import ba.app.redraw.ui.main.canvas.CanvasModule
import com.google.android.material.navigation.NavigationView
import com.skydoves.colorpickerview.listeners.ColorListener
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseBoundActivity<MainViewModel>(), NavigationView.OnNavigationItemSelectedListener {

    override val layoutRId: Int
        get() = R.layout.activity_main
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun bindToViewModel() {
        setSupportActionBar(toolbar)
        setListeners()
        setObservers()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setListeners() {
        fab_paint.setOnClickListener { view ->
            color_picker.visibility = View.VISIBLE
        }

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        color_picker.setColorListener(ColorListener { color, fromUser ->
            color_picker.visibility = View.GONE
            // TODO: get color
        })
    }

    private fun setObservers() {
        viewModel.landingNavigationTrigger.observe(this) {
            startActivity(Intent(this, LandingActivity::class.java))
            finish()
        }
    }
}

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}

@Module
abstract class MainFragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CanvasModule::class])
    abstract fun provideCanvasFragment(): CanvasFragment
}
