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
import kotlinx.android.synthetic.main.view_canvas.view.*
import java.util.*

class CanvasFragment : BaseBoundFragment<CanvasViewModel>() {

    private lateinit var binding: FragmentCanvasBinding

    private lateinit var currPaint: ImageButton
    private lateinit var drawBtn: ImageButton
    private lateinit var newCanvasBtn: ImageButton
    private lateinit var eraseBtn: ImageButton
    private lateinit var saveBtn: ImageButton
    private lateinit var drawView: CanvasView

    override val layoutRId: Int
        get() = R.layout.fragment_canvas
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<CanvasViewModel>
        get() = CanvasViewModel::class.java

    override fun bindToViewModel() {
        binding = viewDataBinding as FragmentCanvasBinding

        drawView = binding.containerCanvas.container_canvas_view.canvas_view
        drawBtn = binding.containerCanvas.container_canvas_view.btn_draw
        newCanvasBtn = binding.containerCanvas.container_canvas_view.btn_new_canvas
        eraseBtn = binding.containerCanvas.container_canvas_view.btn_erase
        saveBtn = binding.containerCanvas.container_canvas_view.btn_save
        val paintLayout = binding.containerCanvas.container_canvas_view.container_colors
        currPaint = paintLayout.getChildAt(0) as ImageButton

        currPaint.setImageDrawable(resources.getDrawable(R.drawable.paint_pressed))
        drawBtn.setOnClickListener(this::onClick)
        newCanvasBtn.setOnClickListener(this::onClick)
        eraseBtn.setOnClickListener(this::onClick)
        saveBtn.setOnClickListener(this::onClick)
    }

    fun paintClicked(view: View) {
        if (view !== currPaint) {
            val imgView = view as ImageButton
            val color = view.getTag().toString()
            drawView.setColor(color)
            imgView.setImageDrawable(resources.getDrawable(R.drawable.paint_pressed))
            currPaint.setImageDrawable(resources.getDrawable(R.drawable.paint))
            currPaint = view
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun onClick(v: View) {
        if (v.id == R.id.btn_draw) {
            drawView.setupDrawing()
        }
        if (v.id == R.id.btn_erase) {
            drawView.setErase(true)
            drawView.setBrushSize(drawView.lastBrushSize)
        }
        if (v.id == R.id.btn_new_canvas) {
            val newDialog = AlertDialog.Builder(context)
            newDialog.setTitle("New drawing")
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?")
            newDialog.setPositiveButton("Yes") { dialog, which ->
                drawView.startNew()
                dialog.dismiss()
            }
            newDialog.setNegativeButton("No") { dialog, which -> dialog.cancel() }
            newDialog.show()
        }
        if (v.id == R.id.btn_save) {
            val saveDialog = AlertDialog.Builder(context)
            saveDialog.setTitle("Save drawing")
            saveDialog.setMessage("Save drawing to device Gallery?")
            saveDialog.setPositiveButton("Yes") { dialog, which ->
                drawView.isDrawingCacheEnabled = true
                val imgSaved = MediaStore.Images.Media.insertImage(
                        activity?.contentResolver, drawView!!.drawingCache,
                        UUID.randomUUID().toString() + ".png", "drawing")
                if (imgSaved != null) {
                    val savedToast = Toast.makeText(context,
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT)
                    savedToast.show()
                } else {
                    val unsavedToast = Toast.makeText(context,
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT)
                    unsavedToast.show()
                }
                drawView.destroyDrawingCache()
            }
            saveDialog.setNegativeButton("No") { dialog, which -> dialog.cancel() }
            saveDialog.show()
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
