package ba.app.redraw.ui.main.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class CanvasView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // drawing path
    private lateinit var drawPath: Path
    private var erase = false

    // drawing and canvas paint
    private lateinit var drawPaint: Paint
    private lateinit var canvasPaint: Paint

    // initial color
    private var paintColor = -0x9a0000

    // canvas
    private lateinit var drawCanvas: Canvas

    // canvas bitmap
    private lateinit var canvasBitmap: Bitmap
    private var brushSize = 0f
    var lastBrushSize = 0f

    init {
        setupDrawing()
    }

    fun startNew() {
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()
    }

    fun setErase(isErase: Boolean) {
        erase = isErase
        if (erase) drawPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) else drawPaint!!.xfermode = null
    }

    fun setBrushSize(newSize: Float) {
        val pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, resources.displayMetrics)
        brushSize = pixelAmount
        drawPaint.strokeWidth = brushSize
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(canvasBitmap, 0f, 0f, canvasPaint)
        canvas.drawPath(drawPath, drawPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawPath.moveTo(touchX, touchY)
            MotionEvent.ACTION_MOVE -> drawPath.lineTo(touchX, touchY)
            MotionEvent.ACTION_UP -> {
                drawCanvas.drawPath(drawPath, drawPaint)
                drawPath.reset()
            }
            else -> return false
        }
        invalidate()
        return true
    }

    fun setColor(newColor: String?) {
        invalidate()
        paintColor = Color.parseColor(newColor)
        drawPaint.color = paintColor
    }

    fun setupDrawing() {
        drawPath = Path()
        drawPaint = Paint()
        brushSize = 20f // TODO: should be set by a variable
        lastBrushSize = brushSize

        drawPaint.run {
            color = paintColor
            isAntiAlias = true
            strokeWidth = brushSize
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
        canvasPaint = Paint(Paint.DITHER_FLAG)
    }
}
