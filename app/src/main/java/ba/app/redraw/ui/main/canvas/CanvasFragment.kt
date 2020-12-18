package ba.app.redraw.ui.main.canvas

import android.app.AlertDialog
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModel
import ba.app.redraw.BR
import ba.app.redraw.R
import ba.app.redraw.databinding.FragmentCanvasBinding
import ba.app.redraw.ui.base.di.viewmodel.ViewModelKey
import ba.app.redraw.ui.base.view.BaseBoundFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import java.util.*


class CanvasFragment : BaseBoundFragment<CanvasViewModel>() {

    private lateinit var binding: FragmentCanvasBinding

    private lateinit var btnDraw: ImageButton
    private lateinit var btnNewCanvas: ImageButton
    private lateinit var btnErase: ImageButton
    private lateinit var btnSave: ImageButton
    private lateinit var drawView: CanvasView

    override val layoutRId: Int
        get() = R.layout.fragment_canvas
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<CanvasViewModel>
        get() = CanvasViewModel::class.java

    override fun bindToViewModel() {
        binding = viewDataBinding as FragmentCanvasBinding

        setupUI()
        setListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun setupColor(color: Int) {
        drawView.setColor(color)
    }

    private fun setupUI() {
        drawView = binding.canvasView
        btnDraw = binding.btnDraw
        btnNewCanvas = binding.btnNewCanvas
        btnErase = binding.btnErase
        btnSave = binding.btnSave
    }

    private fun setListeners() {
        btnDraw.setOnClickListener(this::onClick)
        btnNewCanvas.setOnClickListener(this::onClick)
        btnErase.setOnClickListener(this::onClick)
        btnSave.setOnClickListener(this::onClick)
    }

    private fun onClick(v: View) {
        if (v.id == R.id.btn_draw) {
            drawView.setupDrawing()
        }
        if (v.id == R.id.btn_erase) {
            drawView.setErase(true)
            drawView.setBrushSize(drawView.lastBrushSize)
        }
        if (v.id == R.id.btn_new_canvas) {
            val newDialog = AlertDialog.Builder(context)
            newDialog.run {
                setTitle(context?.getString(R.string.canvas_new_drawing_title))
                setMessage(context?.getString(R.string.canvas_new_drawing_meessage))
                setPositiveButton(context?.getString(R.string.yes)) { dialog, _ ->
                    drawView.startNew()
                    dialog.dismiss()
                }
                setNegativeButton(context?.getString(R.string.no)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
        if (v.id == R.id.btn_save) {
            val saveDialog = AlertDialog.Builder(context)
            saveDialog.run {
                saveDialog.setTitle(context?.getString(R.string.action_save_drawing_title))
                setMessage(context?.getString(R.string.action_save_drawing_message))
                setPositiveButton(context?.getString(R.string.yes)) { _, _ ->
                    drawView.isDrawingCacheEnabled = true
                    val imgSaved = MediaStore.Images.Media.insertImage(
                            activity?.contentResolver, drawView.drawingCache,
                            UUID.randomUUID().toString() + ".png", context?.getString(R.string.filename))
                    if (imgSaved != null) {
                        val savedToast = Toast.makeText(context,
                                context?.getString(R.string.toast_drawing_saved), Toast.LENGTH_SHORT)
                        savedToast.show()
                    } else {
                        val unsavedToast = Toast.makeText(context,
                                context?.getString(R.string.toast_drawing_not_saved), Toast.LENGTH_SHORT)
                        unsavedToast.show()
                    }
                    drawView.destroyDrawingCache()
                }
                setNegativeButton(context?.getString(R.string.no)) { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }
}

@Module
abstract class CanvasModule {

    @Binds
    @IntoMap
    @ViewModelKey(CanvasViewModel::class)
    abstract fun provideCanvasViewModel(canvasViewModel: CanvasViewModel): ViewModel
}
